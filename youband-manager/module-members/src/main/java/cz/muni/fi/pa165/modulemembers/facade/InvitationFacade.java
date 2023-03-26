package cz.muni.fi.pa165.modulemembers.facade;

import cz.muni.fi.pa165.modulemembers.api.InvitationDto;
import cz.muni.fi.pa165.modulemembers.mappers.InvitationMapper;
import cz.muni.fi.pa165.modulemembers.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class InvitationFacade {
    private final InvitationService invitationService;
    private final InvitationMapper invitationMapper;

    @Autowired
    public InvitationFacade(InvitationService invitationService,
                            InvitationMapper invitationMapper) {
        this.invitationService = invitationService;
        this.invitationMapper = invitationMapper;
    }

    public InvitationDto findById(Long id) {
        return invitationMapper.mapToDto(invitationService.findById(id));
    }

    public InvitationDto createInvitation(InvitationDto invitationDto) {
        return invitationMapper.mapToDto(invitationService.createInvitation(invitationMapper.mapFromDto(
                invitationDto)));
    }

    public InvitationDto updateInvitation(Long id, InvitationDto invitationDto) {
        return invitationMapper.mapToDto(invitationService.updateInvitation(id,
                                                                            invitationMapper.mapFromDto(
                                                                                    invitationDto)));
    }

    public void deleteInvitation(Long id){
        invitationService.deleteInvitation(id);
    }
}
