package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.librarymodel.api.UserDto;
import cz.muni.fi.pa165.modulecore.mapper.UserMapper;
import cz.muni.fi.pa165.modulecore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserDto> getAll() {
        return userMapper.mapToList(userService.getAll());
    }

    public UserDto createUser(UserDto userDto) {
        return userMapper.mapToDto(userService.create(userMapper.mapFromDto(userDto)));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        return userMapper.mapToDto(userService.update(id, userMapper.mapFromDto(userDto)));
    }

    public void deleteUser(Long id) {
        userService.delete(id);
    }

    public List<UserDto> getAllUsersWithoutBand() {
        return userMapper.mapToList(userService.getAllUsersWithoutBand());
    }

    public List<UserDto> getUsersFromBandBySongId(Long idSong) {
        return userMapper.mapToList(userService.getUsersFromBandBySongId(idSong));
    }
}
