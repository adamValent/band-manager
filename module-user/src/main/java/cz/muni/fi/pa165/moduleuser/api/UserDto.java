package cz.muni.fi.pa165.moduleuser.api;

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

    public UserDto(Long id, @NotNull String email) {
        this.id = id;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
               Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", email='" + email + '\'' + '}';
    }
}
