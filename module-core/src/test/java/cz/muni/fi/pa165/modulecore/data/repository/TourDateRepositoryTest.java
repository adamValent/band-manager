package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Tour;
import cz.muni.fi.pa165.modulecore.data.model.TourDate;
import cz.muni.fi.pa165.modulecore.data.model.User;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@DirtiesContext
public class TourDateRepositoryTest {

    private TourDateRepository tourDateRepository;
    private TestEntityManager entityManager;
    private TourDate testingTourDate;

    @Autowired
    public TourDateRepositoryTest(TourDateRepository tourDateRepository, TestEntityManager entityManager) {
        this.tourDateRepository = tourDateRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void setUp() {
        User user = new User(null, UserType.MANAGER, "test", "test", "test");
        entityManager.persistAndFlush(user);
        Band band = new Band(null, "TEST", Genre.ROCK, new Byte[]{}, user);
        entityManager.persistAndFlush(band);
        Tour tour = new Tour(null, "TESTING TOUR", List.of(band), new ArrayList<>());
        entityManager.persistAndFlush(tour);

        testingTourDate = new TourDate(null, "TEST CITY", LocalDate.now(), "TEST VENUE", tour);

    }


    @Test
    void findByIdOk() {
        entityManager.persistAndFlush(testingTourDate);

        Optional<TourDate> found = tourDateRepository.findById(testingTourDate.getId());
        assertThat("value is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(testingTourDate)));
    }

    @Test
    void findByIdNotFound() {
        Optional<TourDate> found = tourDateRepository.findById(0L);
        assertThat("tourDate is present", found.isEmpty());
    }

    @Test
    void getAll() {
        entityManager.persistAndFlush(testingTourDate);
        List<TourDate> found = Lists.newArrayList(tourDateRepository.findAll());
        assertThat("wrong number of records", found.size() == 1);
        assertThat("not same items", found.get(0).equals(testingTourDate));
    }

    @Test
    void createTourDateOk() {
        TourDate tourDate = new TourDate(null, "TEST CITY", LocalDate.now(), "TEST VENUE", testingTourDate.getTour());
        TourDate result = tourDateRepository.save(tourDate);
        Tour created = entityManager.find(Tour.class, result.getId());
        assertThat("tourDate is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(tourDate)));
    }

    @Test
    void createTourDateMissingTour() {
        testingTourDate.setTour(null);
        try {
            TourDate result = tourDateRepository.save(testingTourDate);
        } catch (ConstraintViolationException ex) {
            return;
        }
        assertThat("tourDate without tour was created", true);
    }

    @Test
    void updateTourDateOk() {
        entityManager.persistAndFlush(testingTourDate);
        testingTourDate.setCity("TESTING CITY UPDATE");
        TourDate updated = entityManager.find(TourDate.class, testingTourDate.getId());
        assertThat(updated, is(equalTo(testingTourDate)));
    }

    @Test
    void deleteTourDateOk() {
        entityManager.persistAndFlush(testingTourDate);
        tourDateRepository.deleteById(testingTourDate.getId());
        assertThat("album is still present",
                Objects.isNull(entityManager.find(Tour.class, testingTourDate.getId())));
        assertThat("bad delete",Objects.nonNull(entityManager.find(Tour.class, testingTourDate.getTour().getId())));
    }

}
