package cz.muni.fi.pa165.moduledataaccess.rest;

import cz.muni.fi.pa165.moduledataaccess.model.User;
import cz.muni.fi.pa165.moduledataaccess.model.dto.UserDTO;
import cz.muni.fi.pa165.moduledataaccess.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return ResponseEntity.of(userService.getById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody UserDTO user) {
        userService.create(new User(0, user.username()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody User user) {
        userService.update(user);
    }
}
