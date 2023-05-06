package cz.muni.fi.pa165.moduleuser.data.repository;

import cz.muni.fi.pa165.moduleuser.data.model.User;
import org.assertj.core.util.Lists;
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
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByIdOk() {
        User user = new User(null, "mail", "usr");
        entityManager.persistAndFlush(user);
        Optional<User> found = userRepository.findById(user.getId());
        assertThat("user is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(user)));
    }

    @Test
    void findByIdNotFound() {
        Optional<User> found = userRepository.findById(0L);
        assertThat("user is present", found.isEmpty());
    }

    @Test
    void getAll() {
        User user1 = new User(null, "mail1", "usr1");
        User user2 = new User(null, "mail2", "usr2");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        List<User> found = Lists.newArrayList(userRepository.findAll());
        assertThat("wrong number of records", found.size() == 2);
    }

    @Test
    void createUserOk() {
        User user = new User(null, "mail", "sur");
        User result = userRepository.save(user);
        User created = entityManager.find(User.class, result.getId());
        assertThat("user is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(created)));
    }

    @Test
    void updateUserOk() {
        User user = new User(null, "mail", "usr");
        entityManager.persistAndFlush(user);
        user.setEmail("newmail");
        User updated = entityManager.find(User.class, user.getId());
        assertThat(updated, is(equalTo(user)));
    }

    @Test
    void deleteUserOk() {
        User user = new User(null, "mail", "usr");
        entityManager.persistAndFlush(user);
        userRepository.deleteById(user.getId());
        assertThat("entity is present",
                   Objects.isNull(entityManager.find(User.class, user.getId())));
    }
}