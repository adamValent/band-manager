package cz.muni.fi.pa165.modulecore.api;

import cz.muni.fi.pa165.modulecore.data.enums.InvitationStatus;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class InvitationDto {
    private Long id;
    @NotNull
    private Long toUserId;
    @NotNull
    private String message;
    @NotNull
    private InvitationStatus status;
    @NotNull
    private LocalDate dateReceived;
    @NotNull
    private Band band;

    public InvitationDto() {
    }

    public InvitationDto(Long id,
                         Long toUserId,
                         String message,
                         InvitationStatus status,
                         LocalDate dateReceived,
                         Band band) {
        this.id = id;
        this.band = band;
        this.toUserId = toUserId;
        this.message = message;
        this.status = status;
        this.dateReceived = dateReceived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Band getBand() {
        return band;
    }

    public void setBand(@NotNull Band band) {
        this.band = band;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvitationDto invitationDto = (InvitationDto) o;
        return Objects.equals(id, invitationDto.id) && band.equals(invitationDto.band)
                && toUserId.equals(invitationDto.toUserId) && message.equals(invitationDto.message)
                && status == invitationDto.status && dateReceived.equals(invitationDto.dateReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, band, toUserId, message, status, dateReceived);
    }

    @Override
    public String toString() {
        return "InviteDto{" + "id=" + id +
                ", fromBandId=" + band +
                ", toUserId=" + toUserId +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", dateReceived=" + dateReceived + '}';
    }
}
