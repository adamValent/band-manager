package cz.muni.fi.pa165.moduleemail.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Objects;


public class EmailWithoutRecipientsDto {

    @NotNull(message="{message.error.null}")
    @Schema(name = "subject", example = "Tour 13.2.2021", description = "subject of email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subject;
    @NotNull(message="{message.error.null}")
    @Schema(name = "emailBody", example = "Tour re-planned to  13.2.2021", description = "body of email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String emailBody;

    public EmailWithoutRecipientsDto(String subject, String emailBody) {
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
        EmailWithoutRecipientsDto that = (EmailWithoutRecipientsDto) o;
        return Objects.equals(subject, that.subject) && Objects.equals(emailBody, that.emailBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, emailBody);
    }

    @Override
    public String toString() {
        return "EmailDto{" +
                "subject='" + subject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
