package cz.muni.fi.pa165.modulealbums.api;

import cz.muni.fi.pa165.modulealbums.data.enums.Genre;
import cz.muni.fi.pa165.modulealbums.data.model.Song;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class AlbumDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Date releaseDate;
    @NotNull
    private Genre genre;
    private List<Song> songs;

    public AlbumDto() {}

    public AlbumDto(Long id, @NotNull String name,
                    @NotNull Date releaseDate,
                    @NotNull Genre genre,
                    List<Song> songs) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Date getReleaseDate() {
        return releaseDate;
    }

    public @NotNull Genre getGenre() {
        return genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setReleaseDate(@NotNull Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(@NotNull Genre genre) {
        this.genre = genre;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "AlbumDto{" + "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                ", songs=" + songs + '}';
    }
}
