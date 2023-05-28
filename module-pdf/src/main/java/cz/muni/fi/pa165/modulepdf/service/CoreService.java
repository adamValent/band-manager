package cz.muni.fi.pa165.modulepdf.service;

import cz.muni.fi.pa165.librarymodel.api.AlbumDto;
import cz.muni.fi.pa165.librarymodel.api.BandDto;
import cz.muni.fi.pa165.librarymodel.api.SongDto;
import cz.muni.fi.pa165.librarymodel.api.TourDto;
import cz.muni.fi.pa165.modulepdf.data.model.*;
import cz.muni.fi.pa165.modulepdf.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.modulepdf.mapper.AlbumMapper;
import cz.muni.fi.pa165.modulepdf.mapper.TourMapper;
import cz.muni.fi.pa165.modulepdf.mapper.UserMapper;
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


    public List<User> getBandMembers(Long idBand, String token) {
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
            return Objects.requireNonNull(response.getBody()).getMembers().stream().map(userMapper::mapFromDto).toList();
        }
        throw new ResourceNotFoundException();
    }

    public List<Album> getBandAlbums(Long idBand, String token) {
        String url
                = String.format("http://core:8080/albums/allByBand/%s", idBand);
        ResponseEntity<AlbumDto[]> response;

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), AlbumDto[].class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).length == 0) {
                throw new ResourceNotFoundException();
            }
            return Arrays.stream(Objects.requireNonNull(response.getBody())).map(albumMapper::mapFromDto).toList();
        }
        throw new ResourceNotFoundException();
    }

    public List<Tour> getBandTours(Long idBand, String token) {
        String url
                = String.format("http://core:8080/tours/allByBand/%s", idBand);
        ResponseEntity<TourDto[]> response;

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), TourDto[].class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (Objects.requireNonNull(response.getBody()).length == 0) {
                throw new ResourceNotFoundException();
            }
            return Arrays.stream(Objects.requireNonNull(response.getBody())).map(tourMapper::mapFromDto).toList();
        }
        throw new ResourceNotFoundException();
    }

    public List<Song> getAlbumSongs(Long idAlbum, String token) {
        String url
                = String.format("http://core:8080/albums/allByBand/%s", idAlbum);
        ResponseEntity<AlbumDto[]> response;

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), AlbumDto[].class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

        if (response.hasBody()) {
            if (response.getBody() == null) {
                throw new ResourceNotFoundException();
            }
            List<Song> result = new ArrayList<>();
            for (AlbumDto albumDto: response.getBody()) {
                Album album = albumMapper.mapFromDto(albumDto);
                result.addAll(album.getSongs());
            }
            return result;
        }
        throw new ResourceNotFoundException();
    }

    public List<TourDate> getTourDatesOfTour(Long idTour, String token) {
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
            if (response.getBody() == null) {
                throw new ResourceNotFoundException();
            }
            return tourMapper.mapFromDto(Objects.requireNonNull(response.getBody())).getTourDates();
        }
        throw new ResourceNotFoundException();
    }


}
