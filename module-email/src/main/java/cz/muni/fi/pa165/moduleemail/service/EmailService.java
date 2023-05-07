package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
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

    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("youband.manager@gmail.com");
        message.setTo(emailDto.getRecipients());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getEmailBody());
        emailSender.send(message);
    }

    public void sendEmailToAllBandMembers(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                          Long bandId,
                                          String token) {
        sendEmail(new EmailDto(
                emailWithoutRecipientsDto.getSubject(),
                bandService.getMembersEmailFromBandByiD(bandId, token).toArray(String[]::new),
                emailWithoutRecipientsDto.getEmailBody()));
    }

    public void sendEmailToBandManager(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                       Long bandId,
                                       String token) {
        sendEmail(new EmailDto(
                emailWithoutRecipientsDto.getSubject(),
                new String[]{bandService.getManagerEmailFromBandByiD(bandId, token)},
                emailWithoutRecipientsDto.getEmailBody()));
    }

    public void sendEmailToTourBand(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                    Long tourId,
                                    String token) {
        for (Long id: tourService.getBandIdFromTourId(tourId, token)) {
            sendEmailToAllBandMembers(emailWithoutRecipientsDto, id, token);
        }
    }

}
