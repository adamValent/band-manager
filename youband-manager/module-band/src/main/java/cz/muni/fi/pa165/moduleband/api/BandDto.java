package cz.muni.fi.pa165.moduleband.api;

import cz.muni.fi.pa165.moduleband.data.enums.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;


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

    public BandDto() {
    }

    public BandDto(Long id, String name, Style style, Byte[] image, Long managerId) {
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
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
