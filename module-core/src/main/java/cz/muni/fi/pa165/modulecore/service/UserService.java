package cz.muni.fi.pa165.modulecore.service;

import com.google.common.collect.Lists;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.UserRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
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
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        if(!userRepository.existsById(id))
            throw new ResourceNotFoundException("User does not exist.");
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsersWithoutBand() {
        return userRepository.getAllUsersWithoutBand();
    }

    public List<User> getUsersFromBandBySongId(Long idSong) {
        return userRepository.getUsersFromBandBySongId(idSong);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
