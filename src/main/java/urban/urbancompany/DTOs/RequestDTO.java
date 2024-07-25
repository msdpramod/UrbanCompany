package urban.urbancompany.DTOs;

import lombok.Getter;
import lombok.Setter;


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
