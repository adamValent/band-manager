package cz.muni.fi.pa165.modulemembers.rest;

import cz.muni.fi.pa165.modulemembers.api.UserDto;
import cz.muni.fi.pa165.modulemembers.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.modulemembers.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserRestController {
    private final UserFacade userFacade;

    @Autowired
    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userFacade.findById(id));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userFacade.getAll());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userFacade.createUser(userDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @Valid @RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(userFacade.updateUser(id, userDto));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        try {
            userFacade.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> registerUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> loginUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
