package cz.muni.fi.pa165.moduletours.data.repository;

import cz.muni.fi.pa165.moduletours.data.model.Tour;
import cz.muni.fi.pa165.moduletours.data.model.TourDate;
import cz.muni.fi.pa165.moduletours.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TourRepository {
    private final List<Tour> tours = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {
        Tour tour1 = new Tour(100L, "PopTour",
                new ArrayList<String>(List.of("PopBand1","PopBand2")),
                new ArrayList<TourDate>(List.of(
                        new TourDate("New York", LocalDate.of(2023,5,5), "Venue1"),
                        new TourDate("Sydney", LocalDate.of(2023,6,3), "Venue2")
                )));
        Tour tour2 = new Tour(41L, "RockTour",
                new ArrayList<String>(List.of("RockBand1","PopBand2")),
                new ArrayList<TourDate>(List.of(
                        new TourDate("London", LocalDate.of(2023,3,3), "Venue1"),
                        new TourDate("Amsterdam", LocalDate.of(2023,3,12), "Venue2")
                )));
        Tour tour3 = new Tour(32L, "PopTour2",
                new ArrayList<String>(List.of("PopBand1","RockBand2")),
                new ArrayList<TourDate>(List.of(
                        new TourDate("Dublin", LocalDate.of(2023,4,5), "Venue1"),
                        new TourDate("Belfast", LocalDate.of(2023,4,6), "Venue2")
                )));

        tours.add(tour1);
        tours.add(tour2);
        tours.add(tour3);
    }

    public Tour findById(Long id) {
        return tours.stream()
                .filter(tour -> tour.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person was not found."));
    }

    public List<Tour> getAll() {
        return tours;
    }

    public Tour createTour(Tour newTour) {
        newTour.setId(tours.get(tours.size() - 1).getId() + 1);
        tours.add(newTour);
        return newTour;
    }

    public Tour updateTour(Long id, Tour updated) {
        Tour tour = findById(id);

        tour.setName(updated.getName());
        tour.setTourDates(updated.getTourDates());
        tour.setBandList(updated.getBandList());

        return tour;
    }

    public void deleteTour(Long id) {
        Tour tour = findById(id);
        tours.remove(tour);
    }
}
