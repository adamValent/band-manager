package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import cz.muni.fi.pa165.moduleemail.data.model.Email;
import cz.muni.fi.pa165.moduleemail.data.model.EmailWithoutRecipients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final BandService bandService;
    private final TourService tourService;

    @Autowired
    public EmailService(JavaMailSender emailSender,
                        BandService bandService,
                        TourService tourService) {
        this.emailSender = emailSender;
        this.bandService = bandService;
        this.tourService = tourService;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("youband.manager@gmail.com");
        message.setTo(email.getRecipients());
        message.setSubject(email.getSubject());
        message.setText(email.getEmailBody());
        emailSender.send(message);
    }

    public void sendEmailToAllBandMembers(EmailWithoutRecipients emailWithoutRecipients,
                                          Long bandId,
                                          String token) {
        sendEmail(new Email(
                emailWithoutRecipients.getSubject(),
                bandService.getMembersEmailFromBandByiD(bandId, token).toArray(String[]::new),
                emailWithoutRecipients.getEmailBody()));
    }

    public void sendEmailToBandManager(EmailWithoutRecipients emailWithoutRecipients,
                                       Long bandId,
                                       String token) {
        sendEmail(new Email(
                emailWithoutRecipients.getSubject(),
                new String[]{bandService.getManagerEmailFromBandByiD(bandId, token)},
                emailWithoutRecipients.getEmailBody()));
    }

    public void sendEmailToTourBand(EmailWithoutRecipients emailWithoutRecipients,
                                    Long tourId,
                                    String token) {
        for (Long id: tourService.getBandIdFromTourId(tourId, token)) {
            sendEmailToAllBandMembers(emailWithoutRecipients, id, token);
        }
    }

}
