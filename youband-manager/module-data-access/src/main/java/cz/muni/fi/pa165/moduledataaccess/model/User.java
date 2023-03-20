package cz.muni.fi.pa165.moduledataaccess.model;

import jakarta.validation.constraints.NotNull;

public record User(@NotNull long id, @NotNull String username) {
}
