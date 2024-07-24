package urban.urbancompany.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.DTOs.ResponseDTO;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("FakeStoreProductService")
public class FakeStoreProductService implements iProductService{
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductFromResponseDTO(ResponseDTO responseDTO){
        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setTitle(responseDTO.getTitle());
        product.setPrice(responseDTO.getPrice());
        product.setDescription(responseDTO.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(responseDTO.getCategory());
        product.setImageUrl(responseDTO.getImageUrl());
        return product;

    }

    @Override
    public List<Product> getAllProducts() {
        ResponseDTO[] responseDTOList= restTemplate.getForObject("https://fakestoreapi.com/products", ResponseDTO[].class);
        ArrayList<Product> products = new ArrayList<>();
        for (ResponseDTO responseDTO: responseDTOList){
            products.add(getProductFromResponseDTO(responseDTO));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        ResponseDTO responseDTO= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, ResponseDTO.class);
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public List<Product> getAllCategories() {
        ResponseDTO[] responseDTOList= restTemplate.getForObject("https://fakestoreapi.com/products/categories", ResponseDTO[].class);
        ArrayList<Product> categories = new ArrayList<>();
        for (ResponseDTO responseDTO: responseDTOList){
            categories.add(getProductFromResponseDTO(responseDTO));
        }
        return categories;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        ResponseDTO[] responseDTOList= restTemplate.getForObject("https://fakestoreapi.com/products/category/"+categoryName, ResponseDTO[].class);
        ArrayList<Product> products = new ArrayList<>();
        for (ResponseDTO responseDTO: responseDTOList){
            products.add(getProductFromResponseDTO(responseDTO));
        }
        return products;
    }

    @Override
    public Product addProduct(@RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO= restTemplate.postForObject("https://fakestoreapi.com/products/", requestDTO, ResponseDTO.class);
        return getProductFromResponseDTO(responseDTO);
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
        RequestDTO requestDTO = new RequestDTO();
        restTemplate.delete("https://fakestoreapi.com/products/"+id, requestDTO);
    }
}
