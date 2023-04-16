package cz.muni.fi.pa165.moduleuser.facade;

import cz.muni.fi.pa165.moduleuser.api.UserDto;
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

    public UserDto create(UserDto userDto) {
        return userMapper.mapToDto(userService.create(userMapper.mapFromDto(userDto)));
    }

    public UserDto update(UserDto userDto) {
        return userMapper.mapToDto(userService.update(userMapper.mapFromDto(userDto)));
    }

    public UserDto updateEmail(Long id, String email){
        return userMapper.mapToDto(userService.updateEmail(id, email));
    }

    public UserDto updatePassword(Long id, String password){
        return userMapper.mapToDto(userService.updatePassword(id, password));
    }

    public void delete(Long id){
        userService.delete(id);
    }
}
