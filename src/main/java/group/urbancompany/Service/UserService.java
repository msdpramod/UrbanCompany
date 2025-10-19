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
    public User fetchUser(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
    public boolean updateUser(Long id, User upadatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setFirstname(upadatedUser.getFirstname());
                user.setLastname(upadatedUser.getLastname());
                user.setEmail(upadatedUser.getEmail());
                user.setPhoneNumber(upadatedUser.getPhoneNumber());
                return true;
            }
        }
        return false;
    }
}
