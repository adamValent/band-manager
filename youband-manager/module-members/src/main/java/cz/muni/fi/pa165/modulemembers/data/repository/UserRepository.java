package cz.muni.fi.pa165.modulemembers.data.repository;

import cz.muni.fi.pa165.modulemembers.data.enums.UserType;
import cz.muni.fi.pa165.modulemembers.data.model.User;
import cz.muni.fi.pa165.modulemembers.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class UserRepository {
    private final List<User> users = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {
        User user1 = new User(100L, UserType.MANAGER, "manager", "one-hundred", "manager@mail.com");
        User user2 = new User(10L, UserType.BAND_MEMBER, "member1", "ten", "member1@mail.com");
        User user3 = new User(20L, UserType.BAND_MEMBER, "member2", "twenty", "member2@mail.com");
        User user4 = new User(30L, UserType.BAND_MEMBER, "member3", "thirty", "member3@mail.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    public User findById(Long id) {
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Person was not found."));
    }

    public List<User> getAll() {
        return users;
    }

    public User CreateUser(User newUser) {
        newUser.setId(users.get(users.size() - 1).getId() + 1);
        users.add(newUser);
        return newUser;
    }

    public User updateUser(Long id, User updated) {
        User user = findById(id);

        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setEmail(updated.getEmail());

        return user;
    }

    public void deleteUser(Long id) {
        User user = findById(id);
        users.remove(user);
    }
}
