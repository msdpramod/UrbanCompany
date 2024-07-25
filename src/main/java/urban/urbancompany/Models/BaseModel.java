package urban.urbancompany.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt=new Date();
    private Date updatedAt=new Date();
    private boolean isDeleted;
}
