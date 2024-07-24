package urban.urbancompany.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import urban.urbancompany.DTOs.RequestDTO;
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
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllCategories() {
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
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceEntireProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
