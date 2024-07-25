package urban.urbancompany.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ErrorResponseDTO {
    private String message;
    private int statusCode;

    public ErrorResponseDTO() {
        super();
    }

}
