package group.urbancompany.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private UserRole userRole = UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // if user is deleted, address is also deleted
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

