package cz.muni.fi.pa165.modulepdf.service;

import cz.muni.fi.pa165.modulepdf.api.AlbumDto;
import cz.muni.fi.pa165.modulepdf.api.BandDto;
import cz.muni.fi.pa165.modulepdf.api.TourDto;
import cz.muni.fi.pa165.modulepdf.data.model.*;
import cz.muni.fi.pa165.modulepdf.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.modulepdf.mapper.AlbumMapper;
import cz.muni.fi.pa165.modulepdf.mapper.TourMapper;
import cz.muni.fi.pa165.modulepdf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CoreService {

    private final RestTemplate restTemplate;
    private final UserMapper userMapper;
    private final TourMapper tourMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public CoreService(RestTemplate restTemplate,
                       UserMapper userMapper,
                       TourMapper tourMapper,
                       AlbumMapper albumMapper) {
        this.restTemplate = restTemplate;
        this.userMapper = userMapper;
        this.tourMapper = tourMapper;
        this.albumMapper = albumMapper;
    }


    public List<User> getBandMembers(Long idBand) {
        String url
                = String.format("http://core:8080/bands/%s", idBand);
        ResponseEntity<BandDto> response;

        try {
            response = restTemplate.getForEntity(url, BandDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).getMembers().isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return Objects.requireNonNull(response.getBody()).getMembers().stream().map(userMapper::mapFromDto).toList();
        }
        throw new ResourceNotFoundException();
    }

    public List<Album> getBandAlbums(Long idBand) {
        String url
                = String.format("http://core:8080/albums/allByBand/%s", idBand);
        ResponseEntity<List<AlbumDto>> response;

        try {
            response = restTemplate.getForEntity(url, (Class<List<AlbumDto>>) Collections.<AlbumDto>emptyList().getClass());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return Objects.requireNonNull(response.getBody()).stream().map(albumMapper::mapFromDto).toList();
        }
        throw new ResourceNotFoundException();
    }

    public List<Tour> getBandTours(Long idBand) {
        String url
                = String.format("http://core:8080/tours/allByBand/%s", idBand);
        ResponseEntity<List<TourDto>> response;

        try {
            response = restTemplate.getForEntity(url, (Class<List<TourDto>>) Collections.<TourDto>emptyList().getClass());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return Objects.requireNonNull(response.getBody()).stream().map(tourMapper::mapFromDto).toList();
        }
        throw new ResourceNotFoundException();
    }

    public List<Song> getAlbumSongs(Long idAlbum) {
        String url
                = String.format("http://core:8080/albums/allByBand/%s", idAlbum);
        ResponseEntity<AlbumDto> response;

        try {
            response = restTemplate.getForEntity(url, AlbumDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (response.getBody() == null) {
                throw new ResourceNotFoundException();
            }
            return albumMapper.mapFromDto(Objects.requireNonNull(response.getBody())).getSongs();
        }
        throw new ResourceNotFoundException();
    }

    public List<TourDate> getTourDatesOfTour(Long idTour) {
        String url
                = String.format("http://core:8080/tours/%s", idTour);
        ResponseEntity<TourDto> response;

        try {
            response = restTemplate.getForEntity(url, TourDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (response.getBody() == null) {
                throw new ResourceNotFoundException();
            }
            return tourMapper.mapFromDto(Objects.requireNonNull(response.getBody())).getTourDates();
        }
        throw new ResourceNotFoundException();
    }


}
