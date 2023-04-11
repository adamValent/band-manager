package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;

import java.io.Serializable;
import java.util.Arrays;

public class Band implements Serializable {
    private Long id;
    private String name;
    private Genre genre;
    private Byte[] image;
    private Long managerId;

    public Band() {
    }

    public Band(Long id, String name, Genre genre, Byte[] image, Long managerId) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.image = image;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
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

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", image=" + Arrays.toString(image) +
                ", managerId=" + managerId +
                '}';
    }
}
