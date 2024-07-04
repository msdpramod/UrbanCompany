package urban.urbancompany.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
}
