package group.urbancompany.Controller;

import group.urbancompany.DTOs.UserResponse;
import group.urbancompany.Models.User;
import group.urbancompany.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET all users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getUsers();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        Optional<UserResponse> optionalUser = userService.fetchUser(id);
        return optionalUser.isPresent() ? new ResponseEntity<>(optionalUser.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // CREATE new user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        userService.addUser(user);
        UserResponse userResponse = userService.fetchUser(user.getId()).orElse(null);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // UPDATE existing user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> updated = userService.updateUser(id, updatedUser);
        if (updated.isPresent()) {
            UserResponse userResponse = userService.fetchUser(id).orElse(null);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<UserResponse> optionalUser = userService.fetchUser(id);
        if (optionalUser.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
