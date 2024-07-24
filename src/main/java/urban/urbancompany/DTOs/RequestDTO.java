package urban.urbancompany.DTOs;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import urban.urbancompany.Models.Category;

@Getter
@Setter
public class RequestDTO {
    private Long id;
    private String title;
    private String description;
    private int price;
    private String category;
    private String imageUrl;
}
