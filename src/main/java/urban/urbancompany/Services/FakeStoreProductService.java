package urban.urbancompany.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.DTOs.ResponseDTO;
import urban.urbancompany.Excepections.ProductNotFoundException;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier("FakeStoreProductService")
public class FakeStoreProductService implements iProductService{
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductFromResponseDTO(ResponseDTO responseDTO){
        Product product = new Product();
        //product.setId(responseDTO.getI);
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
        assert responseDTOList != null;
        for (ResponseDTO responseDTO: responseDTOList){
            products.add(getProductFromResponseDTO(responseDTO));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        ResponseDTO responseDTO= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, ResponseDTO.class);
        if(responseDTO == null){
            throw new ProductNotFoundException("Product not found with id: "+id);
        }
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public List<Category> getAllCategories() {
        ResponseDTO[] responseDTOList = restTemplate.getForObject("https://fakestoreapi.com/products/categories", ResponseDTO[].class);
        List<Category> categories = new ArrayList<>();
        if (responseDTOList != null) {
            for (ResponseDTO responseDTO : responseDTOList) {
                categories.add(new Category(responseDTO.getCategory()));
            }
        }
        return categories;
    }


    @Override
    public Product addProduct(@RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO= restTemplate.postForObject("https://fakestoreapi.com/products/", requestDTO, ResponseDTO.class);
        assert responseDTO != null;
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public Product updateProduct(Long id, @RequestBody RequestDTO requestDTO) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDTO, ResponseDTO.class);
        HttpMessageConverterExtractor<ResponseDTO> responseExtractor = new HttpMessageConverterExtractor<>(ResponseDTO.class, restTemplate.getMessageConverters());
        ResponseDTO responseDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        assert responseDTO != null;
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public Product replaceEntireProduct(Long id, @RequestBody RequestDTO requestDTO) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDTO, ResponseDTO.class);
        HttpMessageConverterExtractor<ResponseDTO> responseExtractor = new HttpMessageConverterExtractor<>(ResponseDTO.class, restTemplate.getMessageConverters());
        ResponseDTO responseDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        assert responseDTO != null;
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        RequestDTO requestDTO = new RequestDTO();
        restTemplate.delete("https://fakestoreapi.com/products/"+id, requestDTO);
        return true;
    }

}
