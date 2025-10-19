package group.urbancompany.DTOs;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
}
