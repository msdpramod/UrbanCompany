package urban.urbancompany.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Models.Product;
import urban.urbancompany.Services.iProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final iProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") iProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){


        return null;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/categories")
    public List<Product> getAllCategories(){
        return new ArrayList<>();
    }

    @GetMapping("/categories/{catName}")
    public List<Product> getProductsByCategory(@PathVariable("catName") String categoryName){
        return new ArrayList<>();
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){
        return null;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody RequestDTO requestDTO){
        return null;
    }
    @PatchMapping("/{id}")
    public Product replaceEntireProduct(@PathVariable Long id, @RequestBody RequestDTO requestDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
    }


}
