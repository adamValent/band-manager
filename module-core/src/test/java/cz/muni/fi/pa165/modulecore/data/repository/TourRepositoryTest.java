package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.*;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@DirtiesContext
public class TourRepositoryTest {

    private final TourRepository tourRepository;
    private final TestEntityManager entityManager;
    private Tour testingtour;

    @Autowired
    public TourRepositoryTest(TourRepository tourRepository, TestEntityManager entityManager) {
        this.tourRepository = tourRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void setUp() {
        User user = new User(null, UserType.MANAGER, "test", "test", "test");
        entityManager.persistAndFlush(user);
        Band band = new Band(null, "TEST", Genre.ROCK, new Byte[]{}, user);
        entityManager.persistAndFlush(band);

        testingtour = new Tour(null, "TESTING TOUR", List.of(band), new ArrayList<>());
        entityManager.persistAndFlush(testingtour);

    }


    @Test
    void findByIdOk() {
        entityManager.persistAndFlush(testingtour);

        Optional<Tour> found = tourRepository.findById(testingtour.getId());
        assertThat("value is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(testingtour)));
    }

    @Test
    void findByIdNotFound() {
        Optional<Tour> found = tourRepository.findById(0L);
        assertThat("tour is present", found.isEmpty());
    }

    @Test
    void getAll() {
        entityManager.persistAndFlush(testingtour);
        List<Tour> found = Lists.newArrayList(tourRepository.findAll());
        assertThat("wrong number of records", found.size() == 1);
        assertThat("not same items", found.get(0).equals(testingtour));
    }

    @Test
    void createTourOk() {
        Tour tour = new Tour(null, "TESTING TOUR", testingtour.getBandList(), testingtour.getTourDates());
        Tour result = tourRepository.save(tour);
        Tour created = entityManager.find(Tour.class, result.getId());
        assertThat("tour is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(created)));
    }

    @Test
    void createTourMissingBand() {
        testingtour.setBandList(null);
        try {
            Tour result = tourRepository.save(testingtour);
        } catch (ConstraintViolationException ex) {
            return;
        }
        assertThat("tour without band was created", true);
    }

    @Test
    void createTourEmptyBand() {
        testingtour.setBandList(new ArrayList<>());
        try {
            Tour result = tourRepository.save(testingtour);
        } catch (ConstraintViolationException ex) {
            return;
        }
        assertThat("tour without bands was created", true);
    }

    @Test
    void createTourUserNotInDb() {
        Tour tour = new Tour(null, "TESTING TOUR", testingtour.getBandList(), testingtour.getTourDates());
        try {
            Tour result = tourRepository.save(tour);
        } catch (ConstraintViolationException ex) {
            return;
        }
        assertThat("Invitation without user was created", true);
    }

    @Test
    void updateTourOk() {
        entityManager.persistAndFlush(testingtour);
        testingtour.setName("TESTING UPDATE");
        Tour updated = entityManager.find(Tour.class, testingtour.getId());
        assertThat(updated, is(equalTo(testingtour)));
    }

    @Test
    void deleteTourOk() {
        entityManager.persistAndFlush(testingtour);
        tourRepository.deleteById(testingtour.getId());
        assertThat("album is still present",
                Objects.isNull(entityManager.find(Tour.class, testingtour.getId())));
        assertThat("bad delete",Objects.nonNull(entityManager.find(Band.class, testingtour.getBandList().get(0).getId())));
    }

}
