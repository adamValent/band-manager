package cz.muni.fi.pa165.moduledataaccess.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AlbumDTO(@NotNull String name, @NotNull List<SongDTO> songs) {
}
