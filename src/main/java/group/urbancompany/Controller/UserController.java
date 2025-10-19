package group.urbancompany.Controller;


import group.urbancompany.Models.User;
import group.urbancompany.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        // Logic to retrieve all users
        return userService.getUsers();
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user) {
       userService.addUser(user);
         return "User added successfully";
    }
}
