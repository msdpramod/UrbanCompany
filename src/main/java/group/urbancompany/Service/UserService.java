package group.urbancompany.Service;

import group.urbancompany.DTOs.AddressDTO;
import group.urbancompany.DTOs.UserRequest;
import group.urbancompany.DTOs.UserResponse;
import group.urbancompany.Models.Address;
import group.urbancompany.Models.User;
import group.urbancompany.Repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(mapToUserResponse(user));
        }

        return userResponses;
    }

    // CREATE new user from UserRequest
    public UserResponse addUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        if (userRequest.getAddress() != null) {
            AddressDTO addrDTO = userRequest.getAddress();
            Address address = new Address();
            address.setStreet(addrDTO.getStreet());
            address.setCity(addrDTO.getCity());
            address.setState(addrDTO.getState());
            address.setZipCode(addrDTO.getZipCode());
            address.setCountry(addrDTO.getCountry());
            user.setAddress(address);
        }

        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    // GET user by ID
    public Optional<UserResponse> fetchUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return Optional.of(mapToUserResponse(optionalUser.get()));
        } else {
            return Optional.empty();
        }
    }

    // UPDATE user
    @Transactional
    public Optional<UserResponse> updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstname(userRequest.getFirstname());
            user.setLastname(userRequest.getLastname());
            user.setEmail(userRequest.getEmail());
            user.setPhoneNumber(userRequest.getPhoneNumber());

            if (userRequest.getAddress() != null) {
                AddressDTO addrDTO = userRequest.getAddress();
                Address address = user.getAddress() != null ? user.getAddress() : new Address();
                address.setStreet(addrDTO.getStreet());
                address.setCity(addrDTO.getCity());
                address.setState(addrDTO.getState());
                address.setZipCode(addrDTO.getZipCode());
                address.setCountry(addrDTO.getCountry());
                user.setAddress(address);
            }

            return Optional.of(mapToUserResponse(user));
        } else {
            return Optional.empty();
        }
    }

    // DELETE user
    @Transactional
    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper: map User → UserResponse
    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId().toString());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setUserRole(user.getUserRole());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setZipCode(user.getAddress().getZipCode());
            addressDTO.setCountry(user.getAddress().getCountry());
            userResponse.setAddressdto(addressDTO);
        }

        return userResponse;
    }
}
