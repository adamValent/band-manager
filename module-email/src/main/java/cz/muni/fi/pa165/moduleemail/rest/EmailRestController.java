package cz.muni.fi.pa165.moduleemail.rest;

import cz.muni.fi.pa165.moduleemail.CustomConfiguration;
import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.facade.EmailFacade;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "email")
public class EmailRestController {
    private final EmailFacade emailFacade;

    @Autowired
    public EmailRestController(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email was sent."),
            @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1 or test_2.", content = @Content())})
    @PostMapping(path = "")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDto emailDto,
                                          @RequestHeader("Authorization") @Schema(hidden = true) String token) {
        emailFacade.sendEmail(emailDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters to all band members.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email was sent."),
            @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @PostMapping(path = "band/{id}")
    public ResponseEntity<Void> sendEmailToAllBandMembers(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                          @PathVariable(value = "id") Long idBand,
                                                          @RequestHeader("Authorization") @Schema(hidden = true) String token) {
        emailFacade.sendEmailToAllBandMembers(emailWithoutRecipientsDto, idBand, token);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters to band manager.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email was sent."),
            @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_2.", content = @Content())})
    @PostMapping(path = "band/{id}/manager")
    public ResponseEntity<Void> sendEmailToBandManager(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                       @PathVariable(value = "id") Long idBand,
                                                       @RequestHeader("Authorization") @Schema(hidden = true) String token) {
        emailFacade.sendEmailToBandManager(emailWithoutRecipientsDto, idBand, token);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters to tour band.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email was sent."),
            @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @PostMapping(path = "tour/{id}")
    public ResponseEntity<Void> sendEmailToTourBand(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                    @PathVariable(value = "id") Long idTour,
                                                    @RequestHeader("Authorization") @Schema(hidden = true) String token) {
        emailFacade.sendEmailToTourBand(emailWithoutRecipientsDto, idTour, token);
        return ResponseEntity.ok().build();
    }
}
