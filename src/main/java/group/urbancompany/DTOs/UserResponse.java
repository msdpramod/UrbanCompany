package group.urbancompany.DTOs;

import group.urbancompany.Models.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {
    String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private UserRole userRole;
    private AddressDTO addressdto;
}
