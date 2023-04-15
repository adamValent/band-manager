package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Invitation;
import cz.muni.fi.pa165.modulecore.data.repository.InvitationRepository;
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
        return invitationRepository.findById(id);
    }

    public Invitation createInvitation(Invitation invitation) {
        return invitationRepository.createInvitation(invitation);
    }

    public Invitation updateInvitation(Long id, Invitation invitation) {
        return invitationRepository.updateInvitation(id, invitation);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.deleteInvitation(id);
    }
}
