package group.urbancompany.Service;

import group.urbancompany.Models.User;
import group.urbancompany.Repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
         userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            return userRepository.save(user);
        }
        return null;
    }
}
