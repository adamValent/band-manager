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

    public UserDto createUser(UserDto userDto) {
        return userMapper.mapToDto(userService.createUser(userMapper.mapFromDto(userDto)));
    }

    public UserDto updateUserEmail(Long id, String email){
        return userMapper.mapToDto(userService.updateUserEmail(id, email));
    }

    public UserDto updateUserPassword(Long id, String password){
        return userMapper.mapToDto(userService.updateUserPassword(id, password));
    }

    public void deleteUser(Long id){
        userService.deleteUser(id);
    }
}
