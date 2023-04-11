package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Album implements Serializable {
    private Long id;
    private String name;
    private LocalDate releaseDate;
    private Genre genre;
    private List<Song> songs;

    private long bandId;

    public Album() {
    }

    public Album(Long id, String name, LocalDate releaseDate, Genre genre, List<Song> songs, long bandId) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.songs = songs;
        this.bandId = bandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Album album = (Album) obj;
        return id.equals(album.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                ", songs=" + songs +
                ", bandId=" + bandId + '}';
    }
}
