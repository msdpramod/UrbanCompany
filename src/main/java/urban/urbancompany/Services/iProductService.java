package urban.urbancompany.Services;


import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Models.Product;

import java.util.List;

public interface iProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getAllCategories();
    List<Product> getProductsByCategory(String categoryName);
    Product addProduct(RequestDTO requestDTO);
    Product updateProduct(Long id, Product product);
    Product replaceEntireProduct(Long id, Product product);
    void deleteProduct(Long id);
}
