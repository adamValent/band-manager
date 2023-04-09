package cz.muni.fi.pa165.modulepdf.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


public class EmailWithoutRecipientsDto {

    @NotNull
    @Schema(name = "subject", example = "Tour 13.2.2021", description = "subject of email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subject;
    @NotNull
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
    public String toString() {
        return "EmailDto{" +
                "subject='" + subject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
