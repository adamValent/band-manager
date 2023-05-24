package cz.muni.fi.pa165.moduleemail.data.model;

import java.util.Objects;


public class EmailWithoutRecipients {

    private String subject;
    private String emailBody;

    public EmailWithoutRecipients(String subject, String emailBody) {
        this.subject = subject;
        this.emailBody = emailBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        EmailWithoutRecipients that = (EmailWithoutRecipients) o;
        return Objects.equals(subject, that.subject) && Objects.equals(emailBody, that.emailBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, emailBody);
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
