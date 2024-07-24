package urban.urbancompany.Controller;

import org.springframework.web.bind.annotation.*;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Models.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/")
    public List<Product> getAllProducts(){

        return null;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return null;
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
    public Product patchProduct(@PathVariable Long id, @RequestBody RequestDTO requestDTO){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
    }


}
