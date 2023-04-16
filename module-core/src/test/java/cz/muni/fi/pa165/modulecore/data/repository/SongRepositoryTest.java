package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.data.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
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
class SongRepositoryTest {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private TestEntityManager entityManager;
    private Album album;

    @BeforeEach
    void setUp() {
        Band band = new Band(null, "name", Genre.BLUES, new Byte[0], new User());
        Album album = new Album(null, "name", LocalDate.now(), Genre.BLUES, Collections.emptyList(), band);
        entityManager.persist(band);
        entityManager.persist(album);
        entityManager.flush();
        this.album = album;
    }

    @Test
    void findByIdOk() {
        Song song = new Song(null, "title", Duration.ofSeconds(11));
        song.setAlbum(album);
        entityManager.persistAndFlush(song);

        Optional<Song> found = songRepository.findById(song.getId());

        assertThat("value is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(song)));
    }

    @Test
    void findByIdNotFound() {
        Optional<Song> found = songRepository.findById(0L);

        assertThat("value is present", found.isEmpty());
    }

    @Test
    void getAll() {
        Song song1 = new Song(null, "title", Duration.ofSeconds(11));
        Song song2 = new Song(null, "titlez", Duration.ofSeconds(11));
        song1.setAlbum(album);
        song2.setAlbum(album);
        entityManager.persist(song1);
        entityManager.persist(song2);
        entityManager.flush();

        List<Song> found = Lists.newArrayList(songRepository.findAll());

        assertThat("wrong count", found.size() == 2);
    }

    @Test
    void createSongOk() {
        Song song = new Song(null, "title", Duration.ofSeconds(11));
        song.setAlbum(album);

        Song result = songRepository.save(song);

        Song created = entityManager.find(Song.class, result.getId());
        assertThat("entity is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(created)));
    }

    @Test
    void updateSongOk() {
        Song song = new Song(null, "title", Duration.ofSeconds(11));
        song.setAlbum(album);
        entityManager.persistAndFlush(song);

        song.setDuration(Duration.ofSeconds(153));

        Song updated = entityManager.find(Song.class, song.getId());
        assertThat(updated, is(equalTo(song)));
    }

    @Test
    void deleteSongOk() {
        Song song = new Song(null, "title", Duration.ofSeconds(11));
        song.setAlbum(album);
        entityManager.persistAndFlush(song);

        songRepository.deleteById(song.getId());

        assertThat("entity is present", Objects.isNull(entityManager.find(Song.class, song.getId())));
    }
}