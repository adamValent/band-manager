package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "band")
public class Band implements Serializable {
    @Id
    @GeneratedValue(strategy =   GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Genre genre;
    private Byte[] image;
    private Long managerId;
    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Album> albums;
    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invitation> invitations;

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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
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
