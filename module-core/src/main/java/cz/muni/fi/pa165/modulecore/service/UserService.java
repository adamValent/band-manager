package cz.muni.fi.pa165.modulecore.service;

import com.google.common.collect.Lists;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.UserRepository;
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
        return userRepository.findById(id).get();
    }

    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return createUser(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
