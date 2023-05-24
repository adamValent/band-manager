package cz.muni.fi.pa165.modulepdf.data.model;

import cz.muni.fi.pa165.librarymodel.enums.Genre;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Band implements Serializable {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Genre genre;
    @NotNull
    private Byte[] image;
    @NotNull
    private User manager;
    private List<User> members;
    private List<Album> albums;
    private List<Tour> tours;

    public Band(Long id, String name, Genre genre, @NotNull Byte[] image, User manager, List<User> members, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.image = image;
        this.manager = manager;
        this.members = members;
        this.albums = albums;
    }

    public Band(Long id, String name, Genre genre, @NotNull Byte[] image, User manager) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.image = image;
        this.manager = manager;
    }

    public Band() {

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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return Objects.equals(name, band.name) && genre == band.genre && Arrays.equals(image, band.image) && Objects.equals(manager, band.manager) && Objects.equals(members, band.members) && Objects.equals(albums, band.albums);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, genre, manager, members, albums);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", image=" + Arrays.toString(image) +
                ", manager=" + manager +
                ", members=" + members +
                ", albums=" + albums +
                '}';
    }
}
