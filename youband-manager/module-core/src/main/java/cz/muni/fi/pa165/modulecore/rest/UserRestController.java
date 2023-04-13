package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.UserDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find user by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was found."),
                    @ApiResponse(responseCode = "404", description = "User with given ID does not exist.")
            })
    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @Operation(summary = "Get all users")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "All users returned."),
            })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userFacade.getAll());
    }

    @Operation(summary = "Create user.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was created."),
                    @ApiResponse(responseCode = "400", description = "User given to be created cannot be validated."),
            })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userFacade.createUser(userDto));
    }

    @Operation(summary = "Update user by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was updated."),
                    @ApiResponse(responseCode = "400", description = "User given to be updated cannot be validated."),
                    @ApiResponse(responseCode = "404", description = "User with given ID does not exist.")
            })
    @PutMapping(path = "{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userFacade.updateUser(id, userDto));
    }

    @Operation(summary = "Delete user by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was deleted."),
                    @ApiResponse(responseCode = "404", description = "User with given ID does not exist.")
            })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userFacade.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Register user.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was registered."),
                    @ApiResponse(responseCode = "400", description = "User given to be registered cannot be validated.")
            })
    @PostMapping("registration")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Log in user.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was logged in."),
                    @ApiResponse(responseCode = "400", description = "User given to be logged in cannot be validated.")
            })
    @PostMapping("login")
    public ResponseEntity<Void> loginUser(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Log out user.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was logged out."),
                    @ApiResponse(responseCode = "400", description = "User given to be logged out cannot be validated.")
            })
    @PostMapping("logout")
    public ResponseEntity<Void> logoutUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
