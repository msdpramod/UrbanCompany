package urban.urbancompany.Services;


import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Excepections.ProductNotFoundException;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;

import java.util.List;
import java.util.UUID;

public interface iProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Category> getAllCategories();
   // List<Category> getProductsByCategory(String categoryName);
    Product addProduct(RequestDTO requestDTO);
    Product updateProduct(Long id, RequestDTO requestDTO);
    Product replaceEntireProduct(Long id, RequestDTO requestDTO);
    Boolean deleteProduct(Long id);
}
