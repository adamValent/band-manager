package cz.muni.fi.pa165.moduleuser.mapper;

import cz.muni.fi.pa165.librarymodel.api.UserDto;
import cz.muni.fi.pa165.moduleuser.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);

    User mapFromDto(UserDto userDto);
}
