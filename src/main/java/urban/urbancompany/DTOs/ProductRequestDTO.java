package urban.urbancompany.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequestDTO {
    private String title;
    private float price;
    private String description;
    private String imageUrl;
    private String category;
}
