package urban.urbancompany.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Excepections.ProductNotFoundException;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;

import java.util.List;

@Service
@Qualifier("SelfProductService")
public class SelfProductService implements iProductService{
    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return List.of();
    }

    @Override
    public Product addProduct(RequestDTO requestDTO) {
        return null;
    }


    @Override
    public Product updateProduct(Long id, RequestDTO requestDTO) {
        return null;
    }
    @Override
    public Product replaceEntireProduct(Long id, RequestDTO requestDTO) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return false;
    }
}
