package group.urbancompany.Controller;


import group.urbancompany.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private List<User> users = new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        // Logic to retrieve all users
        return users;
    }

    @PostMapping("/api/users")
    public List<User> createUser(@RequestBody User user) {
        users.add(user);
        return users;
    }
}
