package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.modulecore.api.TourDto;
import cz.muni.fi.pa165.modulecore.mapper.TourMapper;
import cz.muni.fi.pa165.modulecore.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourFacade {
    private final TourService tourService;
    private final TourMapper tourMapper;

    @Autowired
    public TourFacade(TourService tourService, TourMapper tourMapper) {
        this.tourService = tourService;
        this.tourMapper = tourMapper;
    }

    public TourDto findById(Long id) {
        return tourMapper.mapToDto(tourService.findById(id));
    }

    public List<TourDto> getAll() {
        return tourMapper.mapToList(tourService.getAll());
    }

    public TourDto createTour(TourDto tourDto) {
        return tourMapper.mapToDto(tourService.createTour(tourMapper.mapFromDto(tourDto)));
    }

    public TourDto updateTour(Long id, TourDto tourDto) {
        return tourMapper.mapToDto(tourService.updateTour(id, tourMapper.mapFromDto(tourDto)));
    }

    public void deleteTour(Long id) {
        tourService.deleteTour(id);
    }
}
