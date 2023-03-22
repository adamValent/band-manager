package cz.muni.fi.pa165.moduledataaccess.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Album(@NotNull long id, @NotNull String name, @NotNull List<Song> songs) {
}
