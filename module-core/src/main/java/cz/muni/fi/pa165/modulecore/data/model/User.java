package cz.muni.fi.pa165.modulecore.data.model;

import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_type")
    @NotNull
    private UserType userType;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    private String lastName;
    @Column(name = "email")
    @NotNull
    private String email;
    @OneToOne()
    @JoinColumn(name = "band_id", referencedColumnName = "id")
    private Band managerOfBand;
    @ManyToOne
    private Band memberOfBand;
    @OneToMany
    private List<Invitation> invitations = Collections.emptyList();


    public User(Long id,
                UserType userType,
                String firstName,
                String lastName,
                String email,
                Band managerOfBand,
                Band memberOfBand, List<Invitation> invitations) {
        this.id = id;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.managerOfBand = managerOfBand;
        this.memberOfBand = memberOfBand;
        this.invitations = invitations;
    }

    public User(Long id, UserType userType, String firstName, String lastName, String email) {
        this.id = id;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Band getManagerOfBand() {
        return managerOfBand;
    }

    public void setManagerOfBand(Band managerOfBand) {
        this.managerOfBand = managerOfBand;
    }

    public Band getMemberOfBand() {
        return memberOfBand;
    }

    public void setMemberOfBand(Band memberOfBand) {
        this.memberOfBand = memberOfBand;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        User user = (User) o;
        return userType == user.userType && Objects.equals(firstName, user.firstName)
               && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
               && Objects.equals(managerOfBand, user.managerOfBand) && Objects.equals(memberOfBand,
                                                                                      user.memberOfBand);
    }

    @Override
    public int hashCode() {
        if (memberOfBand == null) {
            return Objects.hash(userType, firstName, lastName, email, managerOfBand);
        }
        return Objects.hash(userType, firstName, lastName, email, managerOfBand, memberOfBand.getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userType=" + userType +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
