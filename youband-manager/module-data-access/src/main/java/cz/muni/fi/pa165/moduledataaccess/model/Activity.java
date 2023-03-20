package cz.muni.fi.pa165.moduledataaccess.model;

import jakarta.validation.constraints.NotNull;

public record Activity(@NotNull long id, @NotNull String name) {
}
