package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.data.model.User;
import jakarta.persistence.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@DirtiesContext
public class BandRepositoryTest {

    private BandRepository bandRepository;
    private TestEntityManager entityManager;
    private Band band;

    @Autowired
    public BandRepositoryTest(BandRepository bandRepository, TestEntityManager entityManager) {
        this.bandRepository = bandRepository;
        this.entityManager = entityManager;
    }

    /*@BeforeEach
    void setUp() {
        Band band = new Band(1L, "name", Genre.BLUES, new Byte[0], new User());
        entityManager.persist(band);
        entityManager.flush();
        this.band = band;
    }

     */

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

}
