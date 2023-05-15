package cz.muni.fi.pa165.modulecore.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tourdates")
public class TourDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String city;
    @NotNull
    private LocalDate date;
    @NotNull
    private String venue;
    @ManyToOne
    @NotNull
    private Tour tour;

    public TourDate() {
    }

    public TourDate(Long id, String city, LocalDate date, String venue) {
        this.id = id;
        this.city = city;
        this.date = date;
        this.venue = venue;
    }

    public TourDate(Long id, String city, LocalDate date, String venue, Tour tour) {
        this.id = id;
        this.city = city;
        this.date = date;
        this.venue = venue;
        this.tour = tour;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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

    @Override
    public String toString() {
        return "TourDate{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                '}';
    }
}
