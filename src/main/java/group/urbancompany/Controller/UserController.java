package group.urbancompany.Controller;


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

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        Optional<User> user = userService.fetchUser(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.fetchUser(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
       userService.addUser(user);
       return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return  new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
    }
}
