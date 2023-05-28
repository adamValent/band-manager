package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.librarymodel.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 70)
    @NotNull
    private String name;
    @Column(name = "release_date")
    @NotNull
    private LocalDate releaseDate;
    @Column(name = "genre")
    @NotNull
    private Genre genre;
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Song> songs;
    @ManyToOne
    @NotNull
    private Band band;

    public Album(Long id, String name, LocalDate releaseDate, Genre genre, List<Song> songs, Band band) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.songs = songs;
        this.band = band;
    }

    public Album(Long id) {
        this(id, "", LocalDate.MIN, Genre.ROCK, new ArrayList<>(), new Band());
    }

    public Album() {
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

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name) && Objects.equals(releaseDate, album.releaseDate) && genre == album.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, genre);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                '}';
    }
}
