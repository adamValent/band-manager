package cz.muni.fi.pa165.moduleemail.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Objects;


public class EmailDto {

    @NotNull(message="{message.error.null}")
    @Schema(name = "subject", example = "Tour 13.2.2021", description = "subject of email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subject;
    @NotNull(message="{message.error.null}")
    @NotEmpty(message="{message.error.empty}")
    @Schema(name = "recipients", example = "[\"ferko@gmail.com\",\"jozko@gmail.com\"]", requiredMode = Schema.RequiredMode.REQUIRED)
    private String[] recipients;
    @NotNull(message="{message.error.null}")
    @Schema(name = "emailBody", example = "Tour re-planned to  13.2.2021", description = "body of email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String emailBody;

    public EmailDto(String subject, @NotNull String[] recipients, String emailBody) {
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
        EmailDto emailDto = (EmailDto) o;
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
        return "EmailDto{" +
                "subject='" + subject + '\'' +
                ", recipients=" + Arrays.toString(recipients) +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
