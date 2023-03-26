package cz.muni.fi.pa165.modulemembers.rest;

import cz.muni.fi.pa165.modulemembers.api.InvitationDto;
import cz.muni.fi.pa165.modulemembers.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.modulemembers.facade.InvitationFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(path = "api/invitations") public class InvitationRestController {
    private final InvitationFacade invitationFacade;

    @Autowired
    public InvitationRestController(InvitationFacade invitationFacade) {
        this.invitationFacade = invitationFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InvitationDto> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(invitationFacade.findById(id));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<InvitationDto> createInvitation(@Valid @RequestBody InvitationDto invitationDto) {
        return ResponseEntity.ok(invitationFacade.createInvitation(invitationDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InvitationDto> updateInvitation(@PathVariable("id") Long id,
                                                          @Valid @RequestBody InvitationDto invitationDto) {
        try {
            return ResponseEntity.ok(invitationFacade.updateInvitation(id, invitationDto));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable("id") Long id) {
        try {
            invitationFacade.deleteInvitation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
