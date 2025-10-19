package group.urbancompany.Models;

import lombok.Data;

@Data
public class User {
    private  Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
}
