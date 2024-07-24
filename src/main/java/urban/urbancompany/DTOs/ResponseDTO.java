package urban.urbancompany.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String title;
    private String description;
    private int price;
    private String category;
    private String imageUrl;
}
