package urban.urbancompany.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Excepections.ProductNotFoundException;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;
import urban.urbancompany.Repository.CateogryRepository;
import urban.urbancompany.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Qualifier("SelfProductService")
public class SelfProductService implements iProductService{
    ProductRepository productRepository;
    CateogryRepository cateogryRepository;
    RestTemplate restTemplate;

    public SelfProductService(ProductRepository productRepository, CateogryRepository cateogryRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.cateogryRepository = cateogryRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id "+id+" not found");
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = cateogryRepository.findAll();
        return categories;
    }


    @Override
    public Product addProduct(RequestDTO requestDTO) {
        Optional<Product> product = Optional.of(new Product());
        product.get().setTitle(requestDTO.getTitle());
        product.get().setDescription(requestDTO.getDescription());
        product.get().setPrice(requestDTO.getPrice());
        product.get().setImageUrl(requestDTO.getImageUrl());
        Optional<Category> categoryOptional = cateogryRepository.findByName(requestDTO.getCategory());
        if (categoryOptional.isEmpty()){
            Category category = new Category();
            category.setName(requestDTO.getCategory());
            //category.setId(requestDTO.getCategoryId());
            category = cateogryRepository.save(category);
            product.get().setCategory(category);
        } else {
            product.get().setCategory(categoryOptional.get());
        }
        if (product.isEmpty()){
            throw new RuntimeException("Product is required");
        }
        product = Optional.ofNullable(productRepository.save(product.orElse(null)));
        return product.orElse(null);
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
