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

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(EmailDto emailDto) {
        System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(emailDto);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("youband.manager@gmail.com");
        message.setTo(emailDto.getRecipients());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getEmailBody());
        emailSender.send(message);
    }

    public void sendEmailToAllBandMembers(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                          Long bandId) {
        System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(bandId);
        System.out.println(emailWithoutRecipientsDto);
    }

    public void sendEmailToBandManager(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                       Long bandId) {
        System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(bandId);
        System.out.println(emailWithoutRecipientsDto);
    }

    public void sendEmailToTourBand(EmailWithoutRecipientsDto emailWithoutRecipientsDto,
                                    Long tourId) {
        System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(tourId);
        System.out.println(emailWithoutRecipientsDto);
    }

}
