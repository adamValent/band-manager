package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.librarymodel.api.BandDto;
import cz.muni.fi.pa165.librarymodel.api.TourDto;
import cz.muni.fi.pa165.moduleemail.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    public List<Long> getBandIdFromTourId(Long idTour, String token) {
        String url
                = String.format("http://core:8080/tours/%s", idTour);
        ResponseEntity<TourDto> response;

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), TourDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).getBandList().isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return Objects.requireNonNull(response.getBody()).getBandList().stream().map(BandDto::getId).toList();
        }
        throw new ResourceNotFoundException();
    }

}
