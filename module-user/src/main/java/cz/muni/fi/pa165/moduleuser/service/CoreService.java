package cz.muni.fi.pa165.moduleuser.service;

import cz.muni.fi.pa165.moduleuser.api.UserDto;
import cz.muni.fi.pa165.moduleuser.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CoreService {
    private final RestTemplate restTemplate;
    @Value("${ybm.core.url}")
    private String coreUrl;

    @Autowired
    public CoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createUser(UserDto user, String token) {
        String url = String.format("%s/users", coreUrl);
        ResponseEntity<UserDto> response;
        HttpEntity<UserDto> request = prepareRequest(user, token);
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, request, UserDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new ResourceNotFoundException();
        }
    }

    public void updateUser(UserDto user, String token) {
        String url = String.format("%s/users/%d", coreUrl, user.getId());
        ResponseEntity<UserDto> response;
        HttpEntity<UserDto> request = prepareRequest(user, token);
        try {
            response = restTemplate.exchange(url, HttpMethod.PUT, request, UserDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new ResourceNotFoundException();
        }
    }

    private static HttpEntity<UserDto> prepareRequest(UserDto user, String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        return new HttpEntity<>(user, headers);
    }
}
