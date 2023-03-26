package cz.muni.fi.pa165.modulealbums.data.repository;

import cz.muni.fi.pa165.modulealbums.data.enums.Genre;
import cz.muni.fi.pa165.modulealbums.data.model.Album;
import cz.muni.fi.pa165.modulealbums.data.model.Song;
import cz.muni.fi.pa165.modulealbums.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class AlbumRepository {
    private final List<Album> albums = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {
        Album album1 = new Album(100L, "In Utero", LocalDate.of(1993, 9, 21), Genre.ROCK, new ArrayList<>(
                List.of(new Song(1L, "Dumb", Duration.ofSeconds(120)),
                        new Song(2L, "Rape me", Duration.ofSeconds(123)))));
        Album album2 = new Album(200L, "Violator", LocalDate.of(1990, 3, 19), Genre.ROCK, new ArrayList<>(
                List.of(new Song(1L, "Personal Jesus", Duration.ofSeconds(295)),
                        new Song(2L, "Sweetest perfection", Duration.ofSeconds(282)),
                        new Song(3L, "Waiting for the night", Duration.ofSeconds(367)))));
        albums.add(album1);
        albums.add(album2);
    }

    public Album findById(Long id) {
        return albums.stream()
                     .filter(album -> album.getId().equals(id))
                     .findFirst()
                     .orElseThrow(() -> new ResourceNotFoundException("Album was not found."));
    }

    public List<Album> getAll() {return albums;}

    public Album createAlbum(Album newAlbum) {
        newAlbum.setId(albums.get(albums.size() - 1).getId() + 1);
        albums.add(newAlbum);

        return newAlbum;
    }

    public Album updateAlbum(Long id, Album updated) {
        Album album = findById(id);
        album.setName(updated.getName());
        album.setGenre(updated.getGenre());
        album.setReleaseDate(updated.getReleaseDate());

        return album;
    }

    public void deleteAlbum(Long id) {
        Album album = findById(id);
        albums.remove(album);
    }

}
