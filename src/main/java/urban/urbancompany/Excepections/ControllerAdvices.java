package urban.urbancompany.Excepections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import urban.urbancompany.DTOs.ErrorResponseDTO;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handleArithmeticException(ArithmeticException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("Error retrieving product: " + e.getMessage());
        errorResponseDTO.setStatusCode(500);
        return new ResponseEntity<>(errorResponseDTO, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointerException(NullPointerException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("Error retrieving product: " + e.getMessage());
        errorResponseDTO.setStatusCode(404);
        return new ResponseEntity<>(errorResponseDTO, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("Error retrieving product: " + e.getMessage());
        errorResponseDTO.setStatusCode(404);
        return new ResponseEntity<>(errorResponseDTO, org.springframework.http.HttpStatus.NOT_FOUND);
    }

}
