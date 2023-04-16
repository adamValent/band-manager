package cz.muni.fi.pa165.moduleuser.rest;

import cz.muni.fi.pa165.moduleuser.api.UserDto;
import cz.muni.fi.pa165.moduleuser.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users-auth")
public class UserRestController {
    private final UserFacade userFacade;

    @Autowired
    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Operation(summary = "Find user by identification number.")
    @GetMapping(path = "{id}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "User was found."),
                   @ApiResponse(responseCode = "404", description = "User was not found.")})
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @Operation(summary = "Create user.")
    @PostMapping
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was created successfully."),
                    @ApiResponse(responseCode = "400", description = "User to be created cannot be validated.")
            })
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userFacade.create(userDto));
    }

    @Operation(summary = "Update user's email address")
    @PutMapping(path = "{id}")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User's email was updated successfully."),
                    @ApiResponse(responseCode = "404", description = "User could not be found.")
            })
    public ResponseEntity<UserDto> updateUserEmail(@PathVariable("id") Long id, @RequestBody String email){
        return ResponseEntity.ok(userFacade.updateEmail(id , email));
    }

    @Operation(summary = "Update user's password")
    @PutMapping(path = "{id}")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User's password was updated successfully."),
                    @ApiResponse(responseCode = "404", description = "User could not be found.")
            })
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable("id") Long id, @RequestBody String password){
        return ResponseEntity.ok(userFacade.updatePassword(id , password));
    }

    @Operation(summary = "Delete user.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "User was deleted successfully."),
                    @ApiResponse(responseCode = "404", description = "User could not be found.")
            })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        userFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
