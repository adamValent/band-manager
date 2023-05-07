package cz.muni.fi.pa165.moduleemail.api;

import cz.muni.fi.pa165.moduleemail.api.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BandDto {

    private Long id;
    @NotNull
    @Schema(name = "name", example = "Misty", description = "full name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @NotNull
    @Schema(name = "genre", example = "ROCK", description = "band genre", requiredMode = Schema.RequiredMode.REQUIRED)
    private Genre genre;
    @NotNull
    @Schema(name = "image", example = "[\"67\",\"7\",\"89\"]", requiredMode = Schema.RequiredMode.REQUIRED)
    private Byte[] image;
    @Schema(name = "manager", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserDto manager;
    @Schema(name = "members", description = "list of members")
    private List<UserDto> members;

    public BandDto() {
    }

    public BandDto(Long id, String name, Genre genre, @NotNull Byte[] image, UserDto manager, List<UserDto> members) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.image = image;
        this.manager = manager;
        this.members = members;
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

    public UserDto getManager() {
        return manager;
    }

    public void setManager(UserDto manager) {
        this.manager = manager;
    }

    public List<UserDto> getMembers() {
        return members;
    }

    public void setMembers(List<UserDto> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandDto bandDto = (BandDto) o;
        return Objects.equals(id, bandDto.id) && Objects.equals(name, bandDto.name) && genre == bandDto.genre && Arrays.equals(image, bandDto.image) && Objects.equals(manager, bandDto.manager) && Objects.equals(members, bandDto.members);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, genre, manager, members);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "BandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", image=" + Arrays.toString(image) +
                ", manager=" + manager +
                ", members=" + members +
                '}';
    }
}
