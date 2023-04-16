package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@DirtiesContext
public class BandRepositoryTest {
    @Autowired
    private BandRepository bandRepository;
    @Autowired
    private TestEntityManager entityManager;
    private Band band;
    private User user;

    @BeforeEach
    void setUp() {
        User manager = new User(null, UserType.MANAGER, "boss", "boss", "boss@gmail.com");
        entityManager.persistAndFlush(manager);
        this.user = manager;
        Band band = new Band(null, "name", Genre.BLUES, new Byte[0], manager);
        entityManager.persist(band);
        entityManager.flush();
        this.band = band;
    }


    @Test
    void findByIdOk() {
        User user = new User(null, UserType.MANAGER, "TEST", "TEST", "TEST");
        entityManager.persistAndFlush(user);
        Band band1 = new Band(null, "Test", Genre.BLUES, new Byte[0], user);
        entityManager.persistAndFlush(band1);

        Optional<Band> found = bandRepository.findById(band1.getId());

        assertThat("value is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(band1)));
    }

    @Test
    void findByIdNotFound() {
        Optional<Band> found = bandRepository.findById(0L);
        assertThat("band is present", found.isEmpty());
    }

    @Test
    void getAll() {
        User user = new User(null, UserType.MANAGER, "TEST", "TEST", "TEST");
        entityManager.persistAndFlush(user);
        Band band1 = new Band(null, "Test", Genre.BLUES, new Byte[0], user);
        entityManager.persistAndFlush(band1);
        List<Band> bands = Lists.newArrayList(band, band1);
        Iterable<Band> found = bandRepository.findAll();
        assertThat(found, is(equalTo(bands)));
    }

    @Test
    void createBandOk() {
        User user = new User(null, UserType.MANAGER, "TEST", "TEST", "TEST");
        entityManager.persistAndFlush(user);
        Band band1 = new Band(null, "Test", Genre.BLUES, new Byte[0], user);
        bandRepository.save(band1);
        Band found = entityManager.find(Band.class, band1.getId());
        assertThat("band is not present", Objects.nonNull(found));
        assertThat(found, is(equalTo(band1)));
    }

    @Test
    void updateBandOk() {
        Band band = new Band(null, "Test", Genre.BLUES, new Byte[0], user);
        entityManager.persistAndFlush(band);
        band.setName("New band");
        Band updated = entityManager.find(Band.class, band.getId());
        assertThat(updated, is(equalTo(band)));
    }

    @Test
    void deleteBandOk() {
        Band band = new Band(null, "Test", Genre.BLUES, new Byte[0], user);
        entityManager.persistAndFlush(band);
        bandRepository.deleteById(band.getId());
        assertThat("band is still present",
                Objects.isNull(entityManager.find(Band.class, band.getId())));
    }

}
