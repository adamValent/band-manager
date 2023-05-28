package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Tour;
import cz.muni.fi.pa165.modulecore.data.model.TourDate;
import cz.muni.fi.pa165.modulecore.data.repository.TourRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
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
        return tourRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Tour> getAll() {
        return (List<Tour>) tourRepository.findAll();
    }

    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public Tour updateTour(Long id, Tour tour) {
        tour.setId(id);
        for (TourDate tourDate: tour.getTourDates()) {
            tourDate.setTour(tour);
        }
        return tourRepository.save(tour);
    }

    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    public List<Tour> findAllToursByBandId(Long bandId) {
        return tourRepository.findAllByBandId(bandId);
    }
}
