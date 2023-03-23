package cz.muni.fi.pa165.modulealbums.data.model;

import cz.muni.fi.pa165.modulealbums.data.enums.Genre;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Album implements Serializable {
    private Long id;
    private String name;
    private Date releaseDate;
    private Genre genre;

    public Album() {}

    public Album(Long id, String name, Date releaseDate, Genre genre) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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
        return "Album{" + "id=" + id + ", name='" + name + '\'' + ", releaseDate=" + releaseDate + ", genre=" + genre + '}';
    }
}
