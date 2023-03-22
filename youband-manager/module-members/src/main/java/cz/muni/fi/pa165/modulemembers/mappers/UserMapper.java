package cz.muni.fi.pa165.modulemembers.mappers;

import cz.muni.fi.pa165.modulemembers.api.UserDto;
import cz.muni.fi.pa165.modulemembers.data.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);

    User mapFromDto(UserDto userDto);

    List<UserDto> mapToList(List<User> users);
}
