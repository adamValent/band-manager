package cz.muni.fi.pa165.modulemembers.data.repository;

import cz.muni.fi.pa165.modulemembers.data.enums.InvitationStatus;
import cz.muni.fi.pa165.modulemembers.data.model.Invitation;
import cz.muni.fi.pa165.modulemembers.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository public class InvitationRepository {
    private final List<Invitation> invitations = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {
        Invitation i1 = new Invitation(100L,
                                       123L,
                                       321L,
                                       "simple invitation",
                                       InvitationStatus.PENDING,
                                       LocalDate.now());
        Invitation i2 = new Invitation(200L,
                                       111L,
                                       222L,
                                       "invitation to Pinkfloyd",
                                       InvitationStatus.ACCEPTED,
                                       LocalDate.now());
        Invitation i3 = new Invitation(300L,
                                       43L,
                                       88L,
                                       "weak invitation",
                                       InvitationStatus.DECLINED,
                                       LocalDate.now());

        invitations.add(i1);
        invitations.add(i2);
        invitations.add(i3);
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
        invitation.setFromBandId(updated.getFromBandId());
        invitation.setToUserId(updated.getToUserId());
        invitation.setMessage(updated.getMessage());
        invitation.setStatus(updated.getStatus());
        invitation.setDateReceived(updated.getDateReceived());

        return invitation;
    }

    public void deleteInvitation(Long id) {
        Invitation invitation = findById(id);
        invitations.remove(invitation);
    }
}
