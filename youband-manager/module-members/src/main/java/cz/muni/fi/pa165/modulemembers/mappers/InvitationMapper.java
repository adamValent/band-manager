package cz.muni.fi.pa165.modulemembers.mappers;

import cz.muni.fi.pa165.modulemembers.api.InvitationDto;
import cz.muni.fi.pa165.modulemembers.data.model.Invitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") public interface InvitationMapper {
    InvitationDto mapToDto(Invitation invitation);

    Invitation mapFromDto(InvitationDto invitationDto);
}
