package cz.muni.fi.pa165.moduletours.api;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class TourDateDto {
    @NotNull
    private String city;
    @NotNull
    private LocalDate date;
    @NotNull
    private String venue;

    public TourDateDto() {
    }

    public TourDateDto(@NotNull String city,
                       @NotNull LocalDate date,
                       @NotNull String venue) {
        this.city = city;
        this.date = date;
        this.venue = venue;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public @NotNull String getVenue() {
        return venue;
    }

    public void setVenue(@NotNull String venue) {
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourDateDto tourDate = (TourDateDto) o;
        return Objects.equals(city, tourDate.city) && Objects.equals(date, tourDate.date) && Objects.equals(venue, tourDate.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, date, venue);
    }
}
