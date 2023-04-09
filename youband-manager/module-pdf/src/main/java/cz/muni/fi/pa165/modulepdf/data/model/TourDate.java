package cz.muni.fi.pa165.modulepdf.data.model;

import java.time.LocalDate;
import java.util.Objects;

public class TourDate {
    private String city;
    private LocalDate date;
    private String venue;

    public TourDate() {}

    public TourDate(String city, LocalDate date, String venue) {
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
        TourDate tourDate = (TourDate) o;
        return Objects.equals(city, tourDate.city) && Objects.equals(date, tourDate.date) && Objects.equals(venue, tourDate.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, date, venue);
    }
}
