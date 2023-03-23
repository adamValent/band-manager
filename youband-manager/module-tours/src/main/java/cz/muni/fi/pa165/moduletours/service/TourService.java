package cz.muni.fi.pa165.moduletours.service;

import cz.muni.fi.pa165.moduletours.data.model.Tour;
import cz.muni.fi.pa165.moduletours.data.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public Tour findById(Long id) {
        return tourRepository.findById(id);
    }

    public List<Tour> getAll() {
        return tourRepository.getAll();
    }

    public Tour createTour(Tour tour) {
        return tourRepository.createTour(tour);
    }

    public Tour updateTour(Long id, Tour tour) {
        return tourRepository.updateTour(id, tour);
    }

    public void deleteTour(Long id) {
        tourRepository.deleteTour(id);
    }

}
