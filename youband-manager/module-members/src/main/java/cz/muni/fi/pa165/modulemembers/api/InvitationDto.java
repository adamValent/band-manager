package cz.muni.fi.pa165.modulemembers.api;

import cz.muni.fi.pa165.modulemembers.data.enums.InvitationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class InvitationDto {
    private Long id;
    @NotNull
    private Long fromBandId;
    @NotNull
    private Long toUserId;
    @NotNull
    private String message;
    @NotNull
    private InvitationStatus status;
    @NotNull
    private LocalDate dateReceived;

    public InvitationDto() {}

    public InvitationDto(Long id,
                         Long fromBandId,
                         Long toUserId,
                         String message,
                         InvitationStatus status,
                         LocalDate dateReceived) {
        this.id = id;
        this.fromBandId = fromBandId;
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

    public @NotNull Long getFromBandId() {
        return fromBandId;
    }

    public void setFromBandId(@NotNull Long fromBandId) {
        this.fromBandId = fromBandId;
    }

    public @NotNull Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(@NotNull Long toUserId) {
        this.toUserId = toUserId;
    }

    public @NotNull String getMessage() {
        return message;
    }

    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    public @NotNull InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull InvitationStatus status) {
        this.status = status;
    }

    public @NotNull LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(@NotNull LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        InvitationDto invitationDto = (InvitationDto) o;
        return Objects.equals(id, invitationDto.id) && fromBandId.equals(invitationDto.fromBandId)
               && toUserId.equals(invitationDto.toUserId) && message.equals(invitationDto.message)
               && status == invitationDto.status && dateReceived.equals(invitationDto.dateReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromBandId, toUserId, message, status, dateReceived);
    }

    @Override
    public String toString() {
        return "InviteDto{" + "id=" + id +
               ", fromBandId=" + fromBandId +
               ", toUserId=" + toUserId +
               ", message='" + message + '\'' +
               ", status=" + status +
               ", dateReceived=" + dateReceived + '}';
    }
}
