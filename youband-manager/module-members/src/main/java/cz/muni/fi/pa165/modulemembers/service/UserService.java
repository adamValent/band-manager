package cz.muni.fi.pa165.modulemembers.service;

import cz.muni.fi.pa165.modulemembers.data.model.User;
import cz.muni.fi.pa165.modulemembers.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User createUser(User user) {
        return userRepository.CreateUser(user);
    }

    public User updateUser(Long id, User user) {
        return userRepository.updateUser(id, user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
