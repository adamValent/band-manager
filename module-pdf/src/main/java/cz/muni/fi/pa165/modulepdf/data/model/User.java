package cz.muni.fi.pa165.modulepdf.data.model;


import cz.muni.fi.pa165.modulepdf.data.enums.UserType;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Long id;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;

    public User() {}

    public User(Long id, UserType userType, String firstName, String lastName, String email) {
        this.id = id;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        User user = (User) o;
        return Objects.equals(id, user.id) && userType == user.userType
               && firstName.equals(user.firstName) && lastName.equals(user.lastName)
               && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userType, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", userType='" + userType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + "'}";
    }
}
