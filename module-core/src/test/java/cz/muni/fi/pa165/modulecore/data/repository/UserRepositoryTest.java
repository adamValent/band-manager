package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.librarymodel.enums.Genre;
import cz.muni.fi.pa165.librarymodel.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.data.model.User;
import org.assertj.core.util.Lists;
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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByIdOk() {
        User user = new User(null, UserType.MANAGER, "first", "last", "mail");
        entityManager.persistAndFlush(user);
        Optional<User> found = userRepository.findById(user.getId());
        assertThat("user is no present", found.isPresent());
        assertThat(found.get(), is(equalTo(user)));
    }

    @Test
    void findByIdNotFound() {
        Optional<User> found = userRepository.findById(0L);
        assertThat("user is present", found.isEmpty());
    }

    @Test
    void getAll() {
        User user1 = new User(null, UserType.MANAGER, "first", "last", "mail");
        User user2 = new User(null, UserType.BAND_MEMBER, "a", "b", "ab@mail");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        List<User> found = Lists.newArrayList(userRepository.findAll());
        assertThat("wrong number of records", found.size() == 2);
    }

    @Test
    void createUserOk() {
        User user = new User(null, UserType.MANAGER, "first", "last", "mail");
        User result = userRepository.save(user);
        User created = entityManager.find(User.class, result.getId());
        assertThat("user is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(created)));
    }

    @Test
    void updateUserOk() {
        User user = new User(null, UserType.MANAGER, "first", "last", "mail");
        entityManager.persistAndFlush(user);
        user.setEmail("123@mail");
        User updated = entityManager.find(User.class, user.getId());
        assertThat(updated, is(equalTo(user)));
    }

    @Test
    void deleteUserOk() {
        User user = new User(null, UserType.MANAGER, "first", "last", "mail");
        entityManager.persistAndFlush(user);
        userRepository.deleteById(user.getId());
        assertThat("user is still present",
                   Objects.isNull(entityManager.find(User.class, user.getId())));
    }

    @Test
    void getAllUsersWithoutBandAllUsersWithoutBand() {
        User user1 = new User(null, UserType.MANAGER, "first", "last", "mail");
        User user2 = new User(null, UserType.BAND_MEMBER, "a", "b", "ab@mail");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        List<User> found = Lists.newArrayList(userRepository.getAllUsersWithoutBand());
        assertThat("wrong number of records", found.size() == 2);
    }

    @Test
    void getAllUsersWithoutBandSomeHaveBand() {
        User user1 = new User(null, UserType.MANAGER, "first", "last", "mail");
        Band band = new Band(null, "name", Genre.BLUES, new Byte[0], user1);
        user1.setManagerOfBand(band);
        User user2 = new User(null, UserType.BAND_MEMBER, "2", "last", "mail");
        band.setMembers(List.of(user2));
        user2.setMemberOfBand(band);
        entityManager.persist(user1);
        entityManager.persist(band);
        entityManager.persist(user2);
        entityManager.flush();
        List<User> found = Lists.newArrayList(userRepository.getAllUsersWithoutBand());
        assertThat("wrong number of records", found.size() == 1);
    }

    @Test
    void getUsersFromBandBySongId() {
        User user1 = new User(null, UserType.MANAGER, "first", "last", "mail");
        Band band = new Band(null, "name", Genre.BLUES, new Byte[0], user1);
        user1.setManagerOfBand(band);
        User user2 = new User(null, UserType.BAND_MEMBER, "2", "last", "mail");
        band.setMembers(List.of(user2));
        user2.setMemberOfBand(band);
        Album album = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), band);
        band.setAlbums(List.of(album));
        Song song = new Song(null, "titlez", Duration.ofSeconds(11));
        song.setAlbum(album);
        album.setSongs(List.of(song));

        entityManager.persist(user1);
        entityManager.persist(band);
        entityManager.persist(user2);
        entityManager.persist(album);
        entityManager.persist(song);
        entityManager.flush();
        List<User> found = Lists.newArrayList(userRepository.getUsersFromBandBySongId(song.getId()));
        assertThat("wrong number of records", found.size() == 1);
    }

}
