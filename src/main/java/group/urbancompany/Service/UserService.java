package group.urbancompany.Service;

import group.urbancompany.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users =new ArrayList<>();
    public List<User> getUsers() {
        return users;
    }
    public List<User> addUser(User user) {
        users.add(user);
        return users;
    }
}
