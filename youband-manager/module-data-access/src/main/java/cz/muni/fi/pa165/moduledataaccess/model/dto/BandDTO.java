package cz.muni.fi.pa165.moduledataaccess.model.dto;

import cz.muni.fi.pa165.moduledataaccess.model.Activity;
import cz.muni.fi.pa165.moduledataaccess.model.Album;
import cz.muni.fi.pa165.moduledataaccess.model.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BandDTO(
        @NotNull String name,
        @NotNull List<User> members,
        @NotNull List<Activity> activities,
        @NotNull List<Album> albums
) {
}
