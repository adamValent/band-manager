package cz.muni.fi.pa165.moduleuser.data.repository;

import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class UserRepository {
    private final List<User> users = new CopyOnWriteArrayList<>();

    public UserRepository() {
    }

    public User findById(Long id) {
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("User was not found."));
    }

    public User findByEmail(String email) {
        return users.stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Email was not found."));
    }

    public User createUser(User newUser){
        newUser.setId(users.get(users.size()-1).getId() + 1);
        users.add(newUser);
        return newUser;
    }

    public User updateUserEmail(Long id, String newMail){
        User user = findById(id);
        user.setEmail(newMail);
        return user;
    }

    public User updateUserPassword(Long id, String newPassword){
        User user = findById(id);
        user.setPassword(newPassword);
        return user;
    }

    public void deleteUser(Long id){
        User user = findById(id);
        users.remove(user);
    }
}
