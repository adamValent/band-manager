package cz.muni.fi.pa165.moduledataaccess.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Band(
        @NotNull long id,
        @NotNull String name,
        @NotNull List<User> members,
        @NotNull List<Activity> activities,
        @NotNull List<Album> albums) {
}
