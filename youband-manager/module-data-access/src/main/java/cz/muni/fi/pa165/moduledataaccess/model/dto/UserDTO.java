package cz.muni.fi.pa165.moduledataaccess.model.dto;

import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull String username) {
}
