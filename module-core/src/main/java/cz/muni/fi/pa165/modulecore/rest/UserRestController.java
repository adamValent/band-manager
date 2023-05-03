package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.CustomConfiguration;
import cz.muni.fi.pa165.modulecore.api.UserDto;
import cz.muni.fi.pa165.modulecore.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserRestController {
    private final UserFacade userFacade;

    @Autowired
    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Find user by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was found."),
            @ApiResponse(responseCode = "404", description = "User with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All users returned."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userFacade.getAll());
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Create user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was created."),
            @ApiResponse(responseCode = "400", description = "User given to be created cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userFacade.createUser(userDto));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Update user by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was updated."),
            @ApiResponse(responseCode = "400", description = "User given to be updated cannot be validated."),
            @ApiResponse(responseCode = "404", description = "User with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PutMapping(path = "{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userFacade.updateUser(id, userDto));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Delete user by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was deleted."),
            @ApiResponse(responseCode = "404", description = "User with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userFacade.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Find all users without band.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping(path = "withoutBand")
    public ResponseEntity<List<UserDto>> getAllUsersWithoutBand() {
        return ResponseEntity.ok(userFacade.getAllUsersWithoutBand());
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Find users by song ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was found."),
            @ApiResponse(responseCode = "404", description = "User with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping(path = "bySong/{id}")
    public ResponseEntity<List<UserDto>> getUsersFromBandBySongId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userFacade.getUsersFromBandBySongId(id));
    }
}
