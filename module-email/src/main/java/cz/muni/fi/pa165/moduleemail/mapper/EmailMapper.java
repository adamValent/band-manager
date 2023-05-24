package cz.muni.fi.pa165.moduleemail.mapper;

import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.data.model.Email;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailDto mapToDto(Email album);

    Email mapFromDto(EmailDto albumDto);
}
