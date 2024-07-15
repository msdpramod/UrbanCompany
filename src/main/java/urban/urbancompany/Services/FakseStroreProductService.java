package urban.urbancompany.Services;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import urban.urbancompany.Controller.ProductController;
import urban.urbancompany.DTOs.ProductRequestDTO;
import urban.urbancompany.DTOs.ProductResponseDTO;
import urban.urbancompany.Excepections.ProductNotFoundException;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakseStroreProductService implements iProductService{
    static RestTemplate restTemplate;
    // private final ProductController productController;

    public FakseStroreProductService(RestTemplate restTemplate  ) {
        this.restTemplate = restTemplate;

    }
    // Working on fetching all products from database
    @Override
    public List<Product> getAllProducts() {
       // List<ProductResponseDTO> productResponseDTO= restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
        //above gives type erasure error
        ProductResponseDTO[] productResponseDTO= restTemplate.getForObject("https://fakestoreapi.com/products", ProductResponseDTO[].class);
        List<Product> products = new ArrayList<>();
        for (ProductResponseDTO productResponseDTO1: productResponseDTO){
           products.add(getProductFromResponseDTO(productResponseDTO1));
        }
        return products;
    }

    public  Product getProductByid(Long id) throws ProductNotFoundException {
        // hit the Fakestore Api and get json response
        //parse the response and convert it into product

        ProductResponseDTO responseDTO= restTemplate.getForObject("https://fakestoreapi.com/products/"+id, ProductResponseDTO.class);
        if(responseDTO==null){
            throw new ProductNotFoundException("Product with id "+id+" not found");
        }
        Product product=getProductFromResponseDTO( responseDTO);
        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) {
       // ProductResponseDTO productResponseDTO= restTemplate.put("https://fakestoreapi.com/products/"+id, ProductResponseDTO.class);
        // above line is throwing error because void method of put
        // RequestCallback requestCallback = this.httpEntityCallback(responseType);
        //        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters(), this.logger);
        //        return this.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, (Object[])uriVariables);
        // we copy the above code from RestTemplate and modify it to work with execute method from getForObject implementation

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDTO,ProductResponseDTO.class);
        HttpMessageConverterExtractor<ProductResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ProductResponseDTO.class,
                        restTemplate.getMessageConverters());
        ProductResponseDTO responseDTO= restTemplate.execute(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT, requestCallback, responseExtractor);
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public Product patchsomeDetailsInProduct(Long id, ProductRequestDTO productRequestDTO) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDTO,ProductResponseDTO.class);
        HttpMessageConverterExtractor<ProductResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ProductResponseDTO.class,
                        restTemplate.getMessageConverters());
        ProductResponseDTO responseDTO= restTemplate.execute(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PATCH, requestCallback, responseExtractor);
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public Product addProduct(ProductRequestDTO productRequestDTO) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDTO,ProductResponseDTO.class);
        HttpMessageConverterExtractor<ProductResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ProductResponseDTO.class,
                        restTemplate.getMessageConverters());
        ProductResponseDTO responseDTO= restTemplate.execute(
                "https://fakestoreapi.com/products/",
                HttpMethod.POST, requestCallback, responseExtractor);
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public boolean deleteProductById(Long id) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(id,ProductResponseDTO.class);
        HttpMessageConverterExtractor<ProductResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ProductResponseDTO.class,
                        restTemplate.getMessageConverters());
        ProductResponseDTO responseDTO= restTemplate.execute(
                "https://fakestoreapi.com/products/",
                HttpMethod.DELETE, requestCallback, responseExtractor);
        return true;
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
