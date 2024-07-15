package urban.urbancompany.Services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import urban.urbancompany.DTOs.ProductRequestDTO;
import urban.urbancompany.Excepections.ProductNotFoundException;
import urban.urbancompany.Models.Product;

import java.util.List;

public interface iProductService {
      Product getProductByid(Long id)throws ProductNotFoundException;
      List<Product> getAllProducts();
      Product updateProduct(Long id, ProductRequestDTO productRequestDTO);
      Product patchsomeDetailsInProduct(Long id, ProductRequestDTO productRequestDTO);
      Product addProduct(@RequestBody ProductRequestDTO productRequestDTO);
      boolean deleteProductById(@PathVariable Long id);
}
