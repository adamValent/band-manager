package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.api.TourDto;
import cz.muni.fi.pa165.moduleemail.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class TourService {

    private final RestTemplate restTemplate;

    @Autowired
    public TourService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Long> getBandIdFromTourId(Long idTour) {
        String url
                = String.format("http://core:8080/tours/%s", idTour);
        ResponseEntity<TourDto> response
                = restTemplate.getForEntity(url, TourDto.class);

        if (response.hasBody()) {
            return Objects.requireNonNull(response.getBody()).getBandList().stream().map(band -> band.getId()).toList();
        }
        throw new ResourceNotFoundException();
    }

}
