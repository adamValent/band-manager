package cz.muni.fi.pa165.modulepdf.api;

import cz.muni.fi.pa165.modulepdf.data.enums.UserType;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class UserDto {
    private Long id;
    @NotNull
    private UserType userType;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;

    public UserDto() {
    }

    public UserDto(Long id, UserType usrType, String fName, String lName, String email) {
        this.id = id;
        this.userType = usrType;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return userType == userDto.userType && firstName.equals(userDto.firstName)
               && lastName.equals(userDto.lastName) && email.equals(userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userType, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "UserDetailViewDto{" + "id=" + id + ", userType=" + userType + ", firstName='"
               + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + "'}";
    }
}
