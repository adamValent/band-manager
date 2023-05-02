package cz.muni.fi.pa165.moduleemail.rest;


import cz.muni.fi.pa165.moduleemail.ModuleEmailApplication;
import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.facade.EmailFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
            security = @SecurityRequirement(name = ModuleEmailApplication.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters.")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Email was sent."),
                    @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping(path = "")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDto emailDto) {
        emailFacade.sendEmail(emailDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleEmailApplication.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters to all band members.")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Email was sent."),
                    @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping(path = "band/{id}")
    public ResponseEntity<Void> sendEmailToAllBandMembers(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                          @PathVariable(value = "id") Long idBand) {
        emailFacade.sendEmailToAllBandMembers(emailWithoutRecipientsDto, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleEmailApplication.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters to band manager.")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Email was sent."),
                    @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping(path = "band/{id}/manager")
    public ResponseEntity<Void> sendEmailToBandManager(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                       @PathVariable(value = "id") Long idBand) {
        emailFacade.sendEmailToBandManager(emailWithoutRecipientsDto, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleEmailApplication.SECURITY_SCHEME_NAME),
            summary = "Send email with given parameters to tour band.")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Email was sent."),
                    @ApiResponse(responseCode = "400", description = "Email parameters cannot be validated."),
                    @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping(path = "tour/{id}")
    public ResponseEntity<Void> sendEmailToTourBand(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                    @PathVariable(value = "id") Long idTour) {
        emailFacade.sendEmailToTourBand(emailWithoutRecipientsDto, idTour);
        return ResponseEntity.ok().build();
    }
}
