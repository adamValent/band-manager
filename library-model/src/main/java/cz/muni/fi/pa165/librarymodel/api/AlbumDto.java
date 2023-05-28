package cz.muni.fi.pa165.librarymodel.api;

import cz.muni.fi.pa165.librarymodel.enums.Genre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AlbumDto {
    private Long id;
    @NotNull(message = "{message.error.null}")
    private String name;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private Genre genre;
    @Valid
    private List<SongDto> songs;
    @NotNull
    private BandDto band;

    public AlbumDto() {
    }

    public AlbumDto(Long id,
                    String name,
                    LocalDate releaseDate,
                    Genre genre,
                    List<SongDto> songs,
                    BandDto band) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.songs = songs;
        this.band = band;
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

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    public BandDto getBand() {
        return band;
    }

    public void setBand(BandDto band) {
        this.band = band;
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
                ", band=" + band + '}';
    }
}
