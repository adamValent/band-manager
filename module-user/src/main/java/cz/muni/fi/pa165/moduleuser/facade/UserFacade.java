package cz.muni.fi.pa165.moduleuser.facade;

import cz.muni.fi.pa165.moduleuser.api.UserDto;
import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.mapper.UserMapper;
import cz.muni.fi.pa165.moduleuser.service.CoreService;
import cz.muni.fi.pa165.moduleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;
    private final CoreService coreService;

    @Autowired
    public UserFacade(UserService userService, UserMapper userMapper, CoreService coreService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.coreService = coreService;
    }

    @Transactional
    public UserDto findById(Long id) {
        return userMapper.mapToDto(userService.findById(id));
    }

    @Transactional
    public UserDto create(UserDto userDto, String oauthId, String token) {
        User user = new User(userDto.getId(), userDto.getEmail(), oauthId);
        UserDto createdUser = userMapper.mapToDto(userService.create(user));
        coreService.createUser(userDto, token);
        return createdUser;
    }

    @Transactional
    public UserDto update(UserDto userDto, String oauthId, String token) {
        User user = new User(userDto.getId(), userDto.getEmail(), oauthId);
        UserDto updatedUser = userMapper.mapToDto(userService.update(user));
        coreService.updateUser(userDto, token);
        return updatedUser;
    }

    @Transactional
    public UserDto updateEmail(Long id, String email){
        return userMapper.mapToDto(userService.updateEmail(id, email));
    }

    @Transactional
    public void delete(Long id){
        userService.delete(id);
    }
}
