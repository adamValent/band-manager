package cz.muni.fi.pa165.moduleemail.data.model;

import java.util.Arrays;
import java.util.Objects;


public class Email {

    private String subject;
    private String[] recipients;
    private String emailBody;

    public Email(String subject, String[] recipients, String emailBody) {
        this.subject = subject;
        this.recipients = recipients;
        this.emailBody = emailBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email emailDto = (Email) o;
        return Objects.equals(subject, emailDto.subject) && Arrays.equals(recipients, emailDto.recipients) && Objects.equals(emailBody, emailDto.emailBody);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(subject, emailBody);
        result = 31 * result + Arrays.hashCode(recipients);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", recipients=" + Arrays.toString(recipients) +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
