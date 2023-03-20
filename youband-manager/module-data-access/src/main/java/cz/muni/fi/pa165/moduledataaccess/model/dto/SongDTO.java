package cz.muni.fi.pa165.moduledataaccess.model.dto;

import jakarta.validation.constraints.NotNull;

public record SongDTO(@NotNull String name, @NotNull long duration) {
}
