package cz.muni.fi.pa165.moduleemail.facade;


import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailFacade {

    private final EmailService emailService;

    @Autowired
    public EmailFacade(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendEmail(EmailDto emailDto, String token) {
        emailService.sendEmail(emailDto);
    }

    public void sendEmailToAllBandMembers(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                          Long bandId,
                                          String token) {
        emailService.sendEmailToAllBandMembers(emailWithoutRecipientsDto, bandId, token);
    }

    public void sendEmailToBandManager(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                       Long bandId,
                                       String token) {
        emailService.sendEmailToBandManager(emailWithoutRecipientsDto, bandId, token);
    }

    public void sendEmailToTourBand(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                    Long tourId,
                                    String token) {
        emailService.sendEmailToTourBand(emailWithoutRecipientsDto, tourId, token);
    }

}
