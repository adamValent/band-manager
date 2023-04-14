package cz.muni.fi.pa165.moduleuser.service;

import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.data.repository.UserRepository;
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
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    public User updateUserEmail(Long id, String email) {
        return userRepository.updateUserEmail(id, email);
    }

    public User updateUserPassword(Long id, String password) {
        return userRepository.updateUserPassword(id, password);
    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }
}
