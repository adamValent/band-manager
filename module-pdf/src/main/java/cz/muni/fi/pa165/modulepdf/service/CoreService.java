package cz.muni.fi.pa165.modulepdf.service;

import cz.muni.fi.pa165.modulepdf.data.enums.Genre;
import cz.muni.fi.pa165.modulepdf.data.enums.UserType;
import cz.muni.fi.pa165.modulepdf.data.model.*;
import cz.muni.fi.pa165.modulepdf.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CoreService {

    private final HashMap<Long, List<User>> membersOfBands = new HashMap<>();
    private final HashMap<Long, List<Album>> albumsOfBands = new HashMap<>();
    private final HashMap<Long, List<Tour>> toursOfBands = new HashMap<>();
    private final HashMap<Long, List<Song>> songsOfAlbum = new HashMap<>();
    private final HashMap<Long, List<TourDate>> tourDatesOfTour = new HashMap<>();

    @PostConstruct
    private void initMock() {

        membersOfBands.put(1L,
                List.of(
                        new User(1L, UserType.BAND_MEMBER, "Jozko", "Tkr", "Jozko@gmail.com"),
                        new User(2L, UserType.MANAGER, "Ferko", "Ferkovic", "ferko@gmail.com")
                ));

        albumsOfBands.put(1L,
                List.of(
                        new Album(100L, "In Utero", LocalDate.of(1993, 9, 21), Genre.ROCK),
                        new Album(101L, "In Utero2", LocalDate.of(1994, 9, 21), Genre.ROCK)
                ));

    }

    public List<User> getBandMembers(Long idBand) {
        if (membersOfBands.containsKey(idBand)) {
            return membersOfBands.get(idBand);
        }
        throw new ResourceNotFoundException();

    }

    public List<Album> getBandAlbums(Long idBand) {
        if (albumsOfBands.containsKey(idBand)) {
            return albumsOfBands.get(idBand);
        }
        throw new ResourceNotFoundException();

    }

    public List<Tour> getBandTours(Long idBand) {
        if (toursOfBands.containsKey(idBand)) {
            return toursOfBands.get(idBand);
        }
        throw new ResourceNotFoundException();

    }

    public List<Song> getAlbumSongs(Long idBand) {
        if (songsOfAlbum.containsKey(idBand)) {
            return songsOfAlbum.get(idBand);
        }
        throw new ResourceNotFoundException();

    }

    public List<TourDate> getTourDatesOfTour(Long idBand) {
        if (tourDatesOfTour.containsKey(idBand)) {
            return tourDatesOfTour.get(idBand);
        }
        throw new ResourceNotFoundException();

    }


}