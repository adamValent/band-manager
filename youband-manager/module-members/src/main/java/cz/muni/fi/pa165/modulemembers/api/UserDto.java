package cz.muni.fi.pa165.modulemembers.api;

import cz.muni.fi.pa165.modulemembers.data.enums.UserType;
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

    public UserDto() {}

    public UserDto(Long id,
                   @NotNull UserType usrType,
                   @NotNull String fName,
                   @NotNull String lName,
                   @NotNull String email) {
        this.id = id;
        this.userType = usrType;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public @NotNull UserType getUserType() {
        return userType;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserType(@NotNull UserType userType) {
        this.userType = userType;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
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
        return "UserDetailViewDto{" + "id=" + id +
               ", userType=" + userType +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + "'}";
    }
}
