package urban.urbancompany.Excepections;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Throwable {
    private String messsage;
    public ProductNotFoundException(String message){
        super(message);
    }
}
