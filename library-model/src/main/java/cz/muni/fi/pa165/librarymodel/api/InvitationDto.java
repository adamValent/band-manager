package cz.muni.fi.pa165.librarymodel.api;

import cz.muni.fi.pa165.librarymodel.enums.InvitationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class InvitationDto {
    private Long id;
    @NotNull
    private String message;
    @NotNull
    private InvitationStatus status;
    @NotNull
    private LocalDate dateReceived;
    @NotNull
    private BandDto band;
    @NotNull
    private UserDto user;

    public InvitationDto() {
    }

    public InvitationDto(Long id, String message, InvitationStatus status, LocalDate dateReceived, BandDto band, UserDto userDto) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.dateReceived = dateReceived;
        this.band = band;
        this.user = userDto;
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

    public BandDto getBand() {
        return band;
    }

    public void setBand(BandDto band) {
        this.band = band;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvitationDto that = (InvitationDto) o;
        return Objects.equals(id, that.id) && Objects.equals(message, that.message) && status == that.status && Objects.equals(dateReceived, that.dateReceived) && Objects.equals(band, that.band) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, status, dateReceived, band, user);
    }

    @Override
    public String toString() {
        return "InvitationDto{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", dateReceived=" + dateReceived +
                ", band=" + band +
                ", user=" + user +
                '}';
    }
}
