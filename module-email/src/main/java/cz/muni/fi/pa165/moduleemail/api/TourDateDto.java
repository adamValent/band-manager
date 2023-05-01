package cz.muni.fi.pa165.moduleemail.api;

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

    public TourDateDto(String city,
                       LocalDate date,
                       String venue) {
        this.city = city;
        this.date = date;
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
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
