package cz.muni.fi.pa165.modulecore.api;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
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

    private long bandId;

    public AlbumDto(Long id, @NotNull String name,
                    @NotNull LocalDate releaseDate,
                    @NotNull Genre genre,
                    List<SongDto> songs,
                    long bandId) {
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

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public @NotNull Genre getGenre() {
        return genre;
    }

    public void setGenre(@NotNull Genre genre) {
        this.genre = genre;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
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
                ", songs=" + songs +
                ", bandId=" + bandId + '}';
    }
}
