package cz.muni.fi.pa165.moduleuser.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import cz.muni.fi.pa165.moduleuser.data.enums.UserType;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class UserDto {
    private Long id;
    @NotNull
    @Schema(name = "email",
            example = "uco@mail.muni.cz",
            description = "email address",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotNull
    @Schema(name = "userType",
            example = "MANAGER",
            description = "user type",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private UserType userType;
    @NotNull
    @Schema(name = "firstName",
            example = "John",
            description = "first name",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;
    @NotNull
    @Schema(name = "lastName",
            example = "Smith",
            description = "last name",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    public UserDto() {
    }

    public UserDto(
            Long id,
            @NotNull String email,
            @NotNull UserType userType,
            @NotNull String firstName,
            @NotNull String lastName) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull UserType getUserType() {
        return userType;
    }

    public void setUserType(@NotNull UserType userType) {
        this.userType = userType;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
               Objects.equals(email, userDto.email) &&
               Objects.equals(userType, userDto.userType) &&
               Objects.equals(firstName, userDto.firstName) &&
               Objects.equals(lastName, userDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, userType, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
