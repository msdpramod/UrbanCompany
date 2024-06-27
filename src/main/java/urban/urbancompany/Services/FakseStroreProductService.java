package urban.urbancompany.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import urban.urbancompany.DTOs.ProductResponseDTO;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;

@Service
public class FakseStroreProductService implements iProductService{
    static RestTemplate restTemplate;

    public FakseStroreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public  Product getProductByid(Long id) {
        // hit the Fakestore Api and get json response
        //parse the response and convert it into product
        ProductResponseDTO responseDTO= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, ProductResponseDTO.class);
        Product product=getProductFromResponseDTO( responseDTO);
        return product;
    }

    public static Product getProductFromResponseDTO (ProductResponseDTO productResponseDTO){
        Product product = new Product();
       // product.setId(productResponseDTO.getId());
        product.setTitle(productResponseDTO.getTitle());
        product.setPrice(productResponseDTO.getPrice());
        product.setDescription(productResponseDTO.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(productResponseDTO.getCategory());
        product.setImageUrl(productResponseDTO.getImageUrl());
        return product;
    }
}
