package cz.muni.fi.pa165.modulecore.mapper;

import cz.muni.fi.pa165.librarymodel.api.InvitationDto;
import cz.muni.fi.pa165.modulecore.data.model.Invitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvitationMapper {
    InvitationDto mapToDto(Invitation invitation);

    Invitation mapFromDto(InvitationDto invitationDto);
}
