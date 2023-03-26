package cz.muni.fi.pa165.modulealbums.api;

import cz.muni.fi.pa165.modulealbums.data.enums.Genre;
import cz.muni.fi.pa165.modulealbums.data.model.Song;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AlbumDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private Genre genre;
    @Valid
    private List<SongDto> songs;

    public AlbumDto(Long id, @NotNull String name,
                    @NotNull LocalDate releaseDate,
                    @NotNull Genre genre,
                    List<SongDto> songs) {
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

    public @NotNull LocalDate getReleaseDate() {
        return releaseDate;
    }

    public @NotNull Genre getGenre() {
        return genre;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setReleaseDate(@NotNull LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(@NotNull Genre genre) {
        this.genre = genre;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumDto albumDto = (AlbumDto) o;
        return Objects.equals(id, albumDto.id) && Objects.equals(name, albumDto.name) && Objects.equals(releaseDate, albumDto.releaseDate) && genre == albumDto.genre && Objects.equals(songs, albumDto.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, genre, songs);
    }

    @Override
    public String toString() {
        return "AlbumDto{" + "id=" + id +
                ", name='" + name + '\'' +
                ", releaseLocalDate=" + releaseDate +
                ", genre=" + genre +
                ", songs=" + songs + '}';
    }
}
