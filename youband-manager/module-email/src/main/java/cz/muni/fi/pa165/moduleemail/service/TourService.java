package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TourService {

    private final HashMap<Long, Long> tourIdMapsToBandId = new HashMap<>();

    @PostConstruct
    private void initMock() {
        tourIdMapsToBandId.put(1L, 1L);
    }

    public Long getBandIdFromTourId(Long idTour) {
        if (tourIdMapsToBandId.containsKey(idTour)) {
            return tourIdMapsToBandId.get(idTour);
        }
        throw new ResourceNotFoundException();

    }

}
