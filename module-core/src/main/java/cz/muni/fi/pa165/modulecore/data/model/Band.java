package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "band")
public class Band implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Genre genre;
    @NotNull
    private Byte[] image;
    @OneToOne(mappedBy = "managerOfBand")
    @NotNull
    private User manager;
    @OneToMany(mappedBy = "memberOfBand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> members;
    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Album> albums;
    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invitation> invitations;
    @ManyToMany(mappedBy = "bandList")
    private List<Tour> tours;

    public Band(Long id, String name, Genre genre, @NotNull Byte[] image, User manager, List<User> members, List<Album> albums, List<Invitation> invitations) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.image = image;
        this.manager = manager;
        this.members = members;
        this.albums = albums;
        this.invitations = invitations;
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

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
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
        return Objects.equals(name, band.name) && genre == band.genre && Arrays.equals(image, band.image) && Objects.equals(manager, band.manager) && Objects.equals(members, band.members) && Objects.equals(albums, band.albums) && Objects.equals(invitations, band.invitations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, genre, manager, members, albums, invitations);
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
                '}';
    }
}
