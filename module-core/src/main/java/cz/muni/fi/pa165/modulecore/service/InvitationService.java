package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Invitation;
import cz.muni.fi.pa165.modulecore.data.repository.InvitationRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {
    private final InvitationRepository invitationRepository;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public Invitation findById(Long id) {
        return invitationRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Invitation createInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public Invitation updateInvitation(Long id, Invitation invitation) {
        invitation.setId(id);
        return invitationRepository.save(invitation);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.deleteById(id);
    }
}
