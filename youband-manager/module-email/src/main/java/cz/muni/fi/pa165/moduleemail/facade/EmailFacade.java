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

    public void sendEmail(EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }

    public void sendEmailToAllBandMembers(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                          Long bandId) {
        emailService.sendEmailToAllBandMembers(emailWithoutRecipientsDto, bandId);
    }

    public void sendEmailToBandManager(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                          Long bandId) {
        emailService.sendEmailToBandManager(emailWithoutRecipientsDto, bandId);
    }

    public void sendEmailToTourBand(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                    Long tourId) {
        emailService.sendEmailToTourBand(emailWithoutRecipientsDto, tourId);
    }

}
