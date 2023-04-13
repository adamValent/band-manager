package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.InvitationDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.InvitationFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "invitations")
public class InvitationRestController {
    private final InvitationFacade invitationFacade;

    @Autowired
    public InvitationRestController(InvitationFacade invitationFacade) {
        this.invitationFacade = invitationFacade;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<InvitationDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(invitationFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<InvitationDto> createInvitation(@Valid @RequestBody InvitationDto invitationDto) {
        return ResponseEntity.ok(invitationFacade.createInvitation(invitationDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<InvitationDto> updateInvitation(@PathVariable("id") Long id,
                                                          @Valid @RequestBody InvitationDto invitationDto) {
        return ResponseEntity.ok(invitationFacade.updateInvitation(id, invitationDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable("id") Long id) {
        invitationFacade.deleteInvitation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
