package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.InvitationDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.InvitationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find invitation by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Invitation was found."),
                    @ApiResponse(responseCode = "404", description = "Invitation with given ID does not exist.")
            })
    @GetMapping(path = "{id}")
    public ResponseEntity<InvitationDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(invitationFacade.findById(id));
    }

    @Operation(summary = "Create invitation.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Invitation was created."),
                    @ApiResponse(responseCode = "400", description = "Invitation given to be created cannot be validated."),
            })
    @PostMapping
    public ResponseEntity<InvitationDto> createInvitation(@Valid @RequestBody InvitationDto invitationDto) {
        return ResponseEntity.ok(invitationFacade.createInvitation(invitationDto));
    }

    @Operation(summary = "Update Invitation by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Invitation was updated."),
                    @ApiResponse(responseCode = "400", description = "Invitation given to be updated cannot be validated."),
                    @ApiResponse(responseCode = "404", description = "Invitation with given ID does not exist.")
            })
    @PutMapping(path = "{id}")
    public ResponseEntity<InvitationDto> updateInvitation(@PathVariable("id") Long id,
                                                          @Valid @RequestBody InvitationDto invitationDto) {
        return ResponseEntity.ok(invitationFacade.updateInvitation(id, invitationDto));
    }

    @Operation(summary = "Delete invitation by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Invitation was deleted."),
                    @ApiResponse(responseCode = "404", description = "Invitation with given ID does not exist.")
            })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable("id") Long id) {
        invitationFacade.deleteInvitation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}