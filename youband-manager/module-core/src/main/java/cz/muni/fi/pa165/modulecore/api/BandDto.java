package cz.muni.fi.pa165.modulecore.api;

import cz.muni.fi.pa165.modulecore.data.enums.Style;
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
    @Schema(name = "style", example = "ROCK", description = "band style", requiredMode = Schema.RequiredMode.REQUIRED)
    private Style style;
    @NotNull
    @Schema(name = "image", example = "[\"67\",\"7\",\"89\"]", requiredMode = Schema.RequiredMode.REQUIRED)
    private Byte[] image;
    @NotNull
    @Schema(name = "managerId", example = "8", description = "manager ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long managerId;

    public BandDto(Long id,
                   @NotNull String name,
                   @NotNull Style style,
                   @NotNull Byte[] image,
                   @NotNull Long managerId) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.image = image;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull Style getStyle() {
        return style;
    }

    public void setStyle(@NotNull Style style) {
        this.style = style;
    }

    public @NotNull Byte[] getImage() {
        return image;
    }

    public void setImage(@NotNull Byte[] image) {
        this.image = image;
    }

    public @NotNull Long getManagerId() {
        return managerId;
    }

    public void setManagerId(@NotNull Long managerId) {
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
        return Objects.equals(id, bandDto.id) && name.equals(bandDto.name) && style == bandDto.style
                && Arrays.equals(image, bandDto.image) && managerId.equals(bandDto.managerId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, style, managerId);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", style=" + style +
                ", image=" + Arrays.toString(image) +
                ", managerId=" + managerId +
                '}';
    }
}
