package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@DirtiesContext
class AlbumRepositoryTest {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TestEntityManager entityManager;
    private Band band;
    private User manager;

    @BeforeEach
    void setUp() {
        User manager = new User(null, UserType.MANAGER, "boss", "boss", "boss@gmail.com");
        entityManager.persistAndFlush(manager);
        Band band = new Band(null, "name", Genre.BLUES, new Byte[0], manager);
        entityManager.persist(band);
        entityManager.flush();
        this.band = band;
        this.manager = manager;
    }

    @Test
    void findByIdOk() {
        Album album =
                new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        entityManager.persistAndFlush(album);
        Optional<Album> found = albumRepository.findById(album.getId());
        assertThat("album is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(album)));
    }

    @Test
    void findByIdNotFound() {
        Optional<Album> found = albumRepository.findById(0L);
        assertThat("album is present", found.isEmpty());
    }

    @Test
    void getAll() {
        Album album1 =
                new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        Album album2 = new Album(null,
                                 "name2",
                                 LocalDate.now(),
                                 Genre.ROCK,
                                 Collections.emptyList(),
                                 band);
        ;
        entityManager.persist(album1);
        entityManager.persist(album2);
        entityManager.flush();
        List<Album> found = Lists.newArrayList(albumRepository.findAll());
        assertThat("wrong number of records", found.size() == 2);
    }

    @Test
    void createAlbumOk() {
        Album album =
                new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        Album result = albumRepository.save(album);
        Album created = entityManager.find(Album.class, result.getId());
        assertThat("album is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(created)));
    }

    @Test
    void updateAlbumOk() {
        Album album =
                new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        entityManager.persistAndFlush(album);
        album.setName("anotherone");
        Album updated = entityManager.find(Album.class, album.getId());
        assertThat(updated, is(equalTo(album)));
    }

    @Test
    void deleteAlbumOk() {
        Album album =
                new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        entityManager.persistAndFlush(album);
        albumRepository.deleteById(album.getId());
        assertThat("album is still present",
                   Objects.isNull(entityManager.find(Album.class, album.getId())));
    }

    @Test
    void findAllByBandIdMix() {
        Band band2 = new Band(null, "name2", Genre.BLUES, new Byte[0], manager);
        Album album1 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        Album album2 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band2);
        entityManager.persist(band2);
        entityManager.persist(album1);
        entityManager.persist(album2);
        entityManager.flush();

        List<Album> found = Lists.newArrayList(albumRepository.findAllByBandId(band.getId()));

        assertThat(found.size(), is(equalTo(1)));
        assertThat(found.get(0), is(equalTo(album1)));
    }

    @Test
    void findAllByBandIdMultiple() {
        Album album1 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        Album album2 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        entityManager.persist(album1);
        entityManager.persist(album2);
        entityManager.flush();

        List<Album> found = Lists.newArrayList(albumRepository.findAllByBandId(band.getId()));

        assertThat(found.size(), is(equalTo(2)));
    }

    @Test
    void findAllByBandIdNone() {
        Band band2 = new Band(null, "name2", Genre.BLUES, new Byte[0], manager);
        Album album1 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        Album album2 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        entityManager.persist(album1);
        entityManager.persist(album2);
        entityManager.flush();

        List<Album> found = Lists.newArrayList(albumRepository.findAllByBandId(band2.getId()));

        assertThat(found.size(), is(equalTo(0)));
    }
}