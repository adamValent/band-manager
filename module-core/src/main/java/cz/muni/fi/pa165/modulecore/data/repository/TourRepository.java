package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.model.Tour;
import cz.muni.fi.pa165.modulecore.data.model.TourDate;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
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
    }

    public Tour findById(Long id) {
        return tours.stream()
                .filter(tour -> tour.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Tour was not found."));
    }

    public List<Tour> getAll() {
        return tours;
    }

    public Tour createTour(Tour newTour) {
        if (tours.isEmpty()) {
            newTour.setId(1L);
        } else {
            newTour.setId(tours.get(tours.size() - 1).getId() + 1);
        }
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
