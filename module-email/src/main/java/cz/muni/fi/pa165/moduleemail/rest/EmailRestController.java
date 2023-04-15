package cz.muni.fi.pa165.moduleemail.rest;


import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.facade.EmailFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/email")
public class EmailRestController {

    private final EmailFacade emailFacade;

    @Autowired
    public EmailRestController(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }


    @PostMapping(path = "")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDto emailDto) {
        emailFacade.sendEmail(emailDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "band/{id}")
    public ResponseEntity<Void> sendEmailToAllBandMembers(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                          @PathVariable(value = "id") Long idBand) {
        emailFacade.sendEmailToAllBandMembers(emailWithoutRecipientsDto, idBand);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "band/{id}/manager")
    public ResponseEntity<Void> sendEmailToBandManager(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                       @PathVariable(value = "id") Long idBand) {
        emailFacade.sendEmailToBandManager(emailWithoutRecipientsDto, idBand);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "tour/{id}")
    public ResponseEntity<Void> sendEmailToTourBand(@Valid @RequestBody EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                                    @PathVariable(value = "id") Long idTour) {
        emailFacade.sendEmailToTourBand(emailWithoutRecipientsDto, idTour);
        return ResponseEntity.ok().build();
    }



}
