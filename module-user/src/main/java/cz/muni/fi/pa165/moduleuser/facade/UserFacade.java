package cz.muni.fi.pa165.moduleuser.facade;

import cz.muni.fi.pa165.moduleuser.api.UserDto;
import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.mapper.UserMapper;
import cz.muni.fi.pa165.moduleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserFacade(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserDto findById(Long id) {
        return userMapper.mapToDto(userService.findById(id));
    }

    public UserDto create(UserDto userDto, String oauthId) {
        User user = new User(userDto.getId(), userDto.getEmail(), oauthId);
        return userMapper.mapToDto(user);
    }

    public UserDto update(UserDto userDto, String oauthId) {
        User user = new User(userDto.getId(), userDto.getEmail(), oauthId);
        return userMapper.mapToDto(user);
    }

    public UserDto updateEmail(Long id, String email){
        return userMapper.mapToDto(userService.updateEmail(id, email));
    }

    public void delete(Long id){
        userService.delete(id);
    }
}
