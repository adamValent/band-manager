package cz.muni.fi.pa165.modulemembers.data.model;

import cz.muni.fi.pa165.modulemembers.data.enums.InvitationStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Invitation implements Serializable {
    private Long id;
    private Long fromBandId;
    private Long toUserId;
    private String message;
    private InvitationStatus status;
    private LocalDate dateReceived;

    public Invitation() {}

    public Invitation(Long id, Long fromBandId,
                      Long toUserId,
                      String message, InvitationStatus status, LocalDate dateReceived) {
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

    public Long getFromBandId() {
        return fromBandId;
    }

    public void setFromBandId(Long fromBandId) {
        this.fromBandId = fromBandId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Invitation that = (Invitation) o;
        return Objects.equals(id, that.id) && fromBandId.equals(that.fromBandId) && toUserId.equals(
                that.toUserId) && message.equals(that.message) && status == that.status
               && dateReceived.equals(that.dateReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromBandId, toUserId, message, status, dateReceived);
    }

    @Override
    public String toString() {
        return "Invitation{" + "id=" + id +
               ", fromBandId=" + fromBandId +
               ", toUserId=" + toUserId +
               ", message='" + message + '\'' +
               ", status=" + status +
               ", dateReceived=" + dateReceived + '}';
    }
}
