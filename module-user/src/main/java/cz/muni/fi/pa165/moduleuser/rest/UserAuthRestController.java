package cz.muni.fi.pa165.moduleuser.rest;

import cz.muni.fi.pa165.moduleuser.CustomConfiguration;
import cz.muni.fi.pa165.moduleuser.api.UserDto;
import cz.muni.fi.pa165.moduleuser.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users-auth")
public class UserAuthRestController {
    private final UserFacade userFacade;

    @Autowired
    public UserAuthRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Find user by identification number.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was found."),
            @ApiResponse(responseCode = "404", description = "User was not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid.", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1 or test_2.", content = @Content())})
    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Create user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was created successfully."),
            @ApiResponse(responseCode = "400", description = "User to be created cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1 or test_2.", content = @Content())})
    @PostMapping(path = "")
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody UserDto userDto,
            @AuthenticationPrincipal OAuth2IntrospectionAuthenticatedPrincipal principal,
            @RequestHeader("Authorization") @Schema(hidden = true) String token) {
        return ResponseEntity.ok(userFacade.create(userDto, principal.getSubject(), token));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Update user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User's was updated successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1 or test_2.", content = @Content())})
    @PutMapping(path = "{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserDto userDto,
            @AuthenticationPrincipal OAuth2IntrospectionAuthenticatedPrincipal principal,
            @RequestHeader("Authorization") @Schema(hidden = true) String token) {
        userDto.setId(id);
        return ResponseEntity.ok(userFacade.update(userDto, principal.getSubject(), token));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Delete user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was deleted successfully."),
            @ApiResponse(responseCode = "404", description = "User could not be found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1 or test_2.", content = @Content())})
    @DeleteMapping(path = "{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        userFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
