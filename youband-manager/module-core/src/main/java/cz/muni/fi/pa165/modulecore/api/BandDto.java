package cz.muni.fi.pa165.modulecore.api;

import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
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
    @NotNull
    @Schema(name = "managerId", example = "8", description = "manager ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long managerId;

    public BandDto(Long id,
                   String name,
                   Genre genre,
                   Byte[] image,
                   Long managerId) {
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

    public void setImage( Byte[] image) {
        this.image = image;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BandDto bandDto = (BandDto) o;
        return Objects.equals(id, bandDto.id) && name.equals(bandDto.name) && genre == bandDto.genre
                && Arrays.equals(image, bandDto.image) && managerId.equals(bandDto.managerId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, genre, managerId);
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
                ", managerId=" + managerId +
                '}';
    }
}
