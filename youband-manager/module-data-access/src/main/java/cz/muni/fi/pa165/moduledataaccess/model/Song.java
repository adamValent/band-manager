package cz.muni.fi.pa165.moduledataaccess.model;

import jakarta.validation.constraints.NotNull;

public record Song(@NotNull long id, @NotNull String name, @NotNull long duration) {}
