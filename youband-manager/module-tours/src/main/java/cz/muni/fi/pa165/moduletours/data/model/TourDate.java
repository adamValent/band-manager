package cz.muni.fi.pa165.moduletours.data.model;

import java.time.LocalDate;

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
}
