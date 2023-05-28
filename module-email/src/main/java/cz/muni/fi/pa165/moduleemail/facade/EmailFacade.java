package cz.muni.fi.pa165.moduleemail.facade;


import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.mapper.EmailMapper;
import cz.muni.fi.pa165.moduleemail.mapper.EmailWithoutRecipientsMapper;
import cz.muni.fi.pa165.moduleemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailFacade {

    private final EmailService emailService;
    private final EmailMapper emailMapper;
    private final EmailWithoutRecipientsMapper emailWithoutRecipientsMapper;

    @Autowired
    public EmailFacade(EmailService emailService,
                       EmailMapper emailMapper,
                       EmailWithoutRecipientsMapper emailWithoutRecipientsMapper) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
        this.emailWithoutRecipientsMapper = emailWithoutRecipientsMapper;
    }

    public void sendEmail(EmailDto emailDto) {
        emailService.sendEmail(emailMapper.mapFromDto(emailDto));
    }

    public void sendEmailToAllBandMembers(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                          Long bandId,
                                          String token) {
        emailService.sendEmailToAllBandMembers(emailWithoutRecipientsMapper.mapFromDto(emailWithoutRecipientsDto),
                bandId, token);
    }

    public void sendEmailToBandManager(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                       Long bandId,
                                       String token) {
        emailService.sendEmailToBandManager(emailWithoutRecipientsMapper.mapFromDto(emailWithoutRecipientsDto),
                bandId, token);
    }

    public void sendEmailToTourBand(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                    Long tourId,
                                    String token) {
        emailService.sendEmailToTourBand(emailWithoutRecipientsMapper.mapFromDto(emailWithoutRecipientsDto),
                tourId, token);
    }

}
