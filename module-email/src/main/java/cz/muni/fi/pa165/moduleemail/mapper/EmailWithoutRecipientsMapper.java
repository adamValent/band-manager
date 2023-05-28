package cz.muni.fi.pa165.moduleemail.mapper;

import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.data.model.EmailWithoutRecipients;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EmailWithoutRecipientsMapper {
    EmailWithoutRecipientsDto mapToDto(EmailWithoutRecipients album);

    EmailWithoutRecipients mapFromDto(EmailWithoutRecipientsDto albumDto);
}
