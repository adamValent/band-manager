package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.InvitationStatus;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Invitation;
import cz.muni.fi.pa165.modulecore.data.model.User;
import org.assertj.core.util.Lists;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@DirtiesContext
public class InvitationRepositoryTest {

    private InvitationRepository invitationRepository;
    private TestEntityManager entityManager;
    private Invitation testinInvitation;

    @Autowired
    public InvitationRepositoryTest(InvitationRepository invitationRepository, TestEntityManager entityManager) {
        this.invitationRepository = invitationRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void setUp() {
        User user = new User(null, UserType.MANAGER, "test", "test", "test");
        entityManager.persistAndFlush(user);
        Band band = new Band(null, "TEST", Genre.ROCK, new Byte[]{}, user);
        entityManager.persistAndFlush(band);
        testinInvitation = new Invitation(null, "TEST invitation", InvitationStatus.PENDING, LocalDate.now(), band, user);

    }


    @Test
    void findByIdOk() {
        entityManager.persistAndFlush(testinInvitation);

        Optional<Invitation> found = invitationRepository.findById(testinInvitation.getId());
        assertThat("value is not present", found.isPresent());
        assertThat(found.get(), is(equalTo(testinInvitation)));
    }

    @Test
    void findByIdNotFound() {
        Optional<Invitation> found = invitationRepository.findById(0L);
        assertThat("invitation is present", found.isEmpty());
    }

    @Test
    void getAll() {
        entityManager.persistAndFlush(testinInvitation);
        List<Invitation> found = Lists.newArrayList(invitationRepository.findAll());
        assertThat("wrong number of records", found.size() == 1);
        assertThat("not same items", found.get(0).equals(testinInvitation));
    }

    @Test
    void createInvitationOk() {
        Invitation result = invitationRepository.save(testinInvitation);
        Invitation created = entityManager.find(Invitation.class, result.getId());
        assertThat("invitation is not present", Objects.nonNull(created));
        assertThat(result, is(equalTo(created)));
    }

    @Test
    void createInvitationMissingBand() {
        testinInvitation.setBand(null);
        try {
            Invitation result = invitationRepository.save(testinInvitation);
        } catch (ConstraintViolationException ex) {
            return;
        }
        assertThat("Invitation without band was created", true);
    }

    @Test
    void createInvitationMissingUser() {
        testinInvitation.setUser(null);
        try {
            Invitation result = invitationRepository.save(testinInvitation);
        } catch (ConstraintViolationException ex) {
            return;
        }
        assertThat("Invitation without user was created", true);
    }

    @Test
    void createInvitationUserNotInDb() {
        testinInvitation.setUser(new User());
        try {
            Invitation result = invitationRepository.save(testinInvitation);
        } catch (InvalidDataAccessApiUsageException ex) {
            return;
        }
        assertThat("Invitation without user was created", true);
    }

    @Test
    void updateInvitationOk() {
        entityManager.persistAndFlush(testinInvitation);
        testinInvitation.setMessage("TESTING UPDATE");
        Invitation updated = entityManager.find(Invitation.class, testinInvitation.getId());
        assertThat(updated, is(equalTo(testinInvitation)));
    }

    @Test
    void deleteInvitationOk() {
        entityManager.persistAndFlush(testinInvitation);
        invitationRepository.deleteById(testinInvitation.getId());
        assertThat("invitation is still present",
                Objects.isNull(entityManager.find(Invitation.class, testinInvitation.getId())));
    }

}
