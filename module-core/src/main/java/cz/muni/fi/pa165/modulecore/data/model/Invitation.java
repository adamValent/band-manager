package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.modulecore.data.enums.InvitationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "invitation")
public class Invitation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long toUserId;
    private String message;
    private InvitationStatus status;
    private LocalDate dateReceived;
    @ManyToOne
    @NotNull
    private Band band;

    public Invitation() {
    }

    public Invitation(Long id,
                      Long toUserId,
                      String message, InvitationStatus status, LocalDate dateReceived, Band band) {
        this.id = id;
        this.toUserId = toUserId;
        this.message = message;
        this.status = status;
        this.dateReceived = dateReceived;
        this.band = band;
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

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invitation that = (Invitation) o;
        return Objects.equals(id, that.id) && band.equals(that.band) && toUserId.equals(
                that.toUserId) && message.equals(that.message) && status == that.status
                && dateReceived.equals(that.dateReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, band, toUserId, message, status, dateReceived);
    }

    @Override
    public String toString() {
        return "Invitation{" + "id=" + id +
                ", fromBandId=" + band +
                ", toUserId=" + toUserId +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", dateReceived=" + dateReceived + '}';
    }
}
