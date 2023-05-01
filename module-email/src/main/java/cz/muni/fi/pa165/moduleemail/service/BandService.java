package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.api.BandDto;
import cz.muni.fi.pa165.moduleemail.api.UserDto;
import cz.muni.fi.pa165.moduleemail.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class BandService {

    private final RestTemplate restTemplate;

    @Autowired
    public BandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getMembersEmailFromBandByiD(Long idBand) {
        String url
                = String.format("http://core:8080/bands/%s", idBand);
        ResponseEntity<BandDto> response
                = restTemplate.getForEntity(url, BandDto.class);

        if (response.hasBody()) {
            return Objects.requireNonNull(response.getBody()).getMembers().stream().map(UserDto::getEmail).toList();
        }
        throw new ResourceNotFoundException();
    }

    public String getManagerEmailFromBandByiD(Long idBand) {
        String url
                = String.format("http://core:8080/bands/%s", idBand);
        ResponseEntity<BandDto> response
                = restTemplate.getForEntity(url, BandDto.class);

        if (response.hasBody()) {
            return Objects.requireNonNull(response.getBody()).getManager().getEmail();
        }
        throw new ResourceNotFoundException();
    }



}
