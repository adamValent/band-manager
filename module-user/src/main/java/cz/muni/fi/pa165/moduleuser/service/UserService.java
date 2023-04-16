package cz.muni.fi.pa165.moduleuser.service;

import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.data.repository.UserRepository;
import cz.muni.fi.pa165.moduleuser.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User updateEmail(Long id, String email) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User updatePassword(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
