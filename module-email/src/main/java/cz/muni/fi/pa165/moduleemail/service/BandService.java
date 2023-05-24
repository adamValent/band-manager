package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.api.BandDto;
import cz.muni.fi.pa165.moduleemail.api.UserDto;
import cz.muni.fi.pa165.moduleemail.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BandService {

    private final RestTemplate restTemplate;

    @Autowired
    public BandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getMembersEmailFromBandByiD(Long idBand, String token) {
        String url
                = String.format("http://core:8080/bands/%s", idBand);
        ResponseEntity<BandDto> response;

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BandDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).getMembers().isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return Objects.requireNonNull(response.getBody()).getMembers().stream().map(UserDto::getEmail).toList();
        }
        throw new ResourceNotFoundException();
    }

    public String getManagerEmailFromBandByiD(Long idBand, String token) {
        String url
                = String.format("http://core:8080/bands/%s", idBand);
        ResponseEntity<BandDto> response;

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), BandDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).getManager() == null) {
                throw new ResourceNotFoundException();
            }
            return Objects.requireNonNull(response.getBody()).getManager().getEmail();
        }
        throw new ResourceNotFoundException();
    }



}
