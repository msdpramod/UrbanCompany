package group.urbancompany.Service;

import group.urbancompany.DTOs.ProductRequest;
import group.urbancompany.DTOs.ProductResponse;
import group.urbancompany.Models.Product;
import group.urbancompany.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE a new product
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = mapToProduct(productRequest, new Product());
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    // GET all products
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAllByActiveTrue();
        List<ProductResponse> responses = new ArrayList<>();
        for (Product p : products) {
            responses.add(mapToProductResponse(p));
        }
        return responses;
    }

    // GET product by ID
    public Optional<ProductResponse> getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductResponse response = mapToProductResponse(optionalProduct.get());
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }


    // UPDATE product
    @Transactional
    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = mapToProduct(productRequest, optionalProduct.get());
            return Optional.of(mapToProductResponse(product));
        }
        return Optional.empty();
    }

    // DELETE product
    @Transactional
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            productRepository.save(product);  // Update product status
            return true;
        }
        return false;
    }


    // Helper: map ProductRequest → Product
    private Product mapToProduct(ProductRequest request, Product product) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        if (product.getActive() == null) {
            product.setActive(true);
        }
        return product;
    }

    // Helper: map Product → ProductResponse
    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategory(product.getCategory());
        response.setImageUrl(product.getImageUrl());
        response.setActive(product.getActive());
        return response;
    }
}
