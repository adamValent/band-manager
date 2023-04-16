package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.InvitationStatus;
import cz.muni.fi.pa165.modulecore.data.model.Invitation;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InvitationRepository {
    private final List<Invitation> invitations = new CopyOnWriteArrayList<>();

    public InvitationRepository() {
    }

    public Invitation findById(Long id) {
        return invitations.stream()
                .filter(invitation -> invitation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Invitation not found"));
    }

    public Invitation createInvitation(Invitation newInvitation) {
        newInvitation.setId(invitations.get(invitations.size() - 1).getId() + 1);
        invitations.add(newInvitation);

        return newInvitation;
    }

    public Invitation updateInvitation(Long id, Invitation updated) {
        Invitation invitation = findById(id);
        invitation.setBand(updated.getBand());
        invitation.setMessage(updated.getMessage());
        invitation.setStatus(updated.getStatus());
        invitation.setDateReceived(updated.getDateReceived());
        invitation.setUser(updated.getUser());

        return invitation;
    }

    public void deleteInvitation(Long id) {
        Invitation invitation = findById(id);
        invitations.remove(invitation);
    }
}
