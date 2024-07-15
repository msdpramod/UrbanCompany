package urban.urbancompany.Excepections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import urban.urbancompany.DTOs.ErrorResponseDTO;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handleArithmeticException(ArithmeticException e){
        ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setMessage("Arithmetic Exception Occured");
        ResponseEntity responseEntity= new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointerException(NullPointerException e){
        ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setMessage("Null Pointer Exception Occured");
        ResponseEntity responseEntity= new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException e){
        ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setMessage("Product Not Found Exception Occured");
        ResponseEntity responseEntity= new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e){
        ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setMessage("Exception Occured");
        ResponseEntity responseEntity= new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
} 
