package urban.urbancompany.Controller;

import org.springframework.web.bind.annotation.*;
import urban.urbancompany.DTOs.ProductRequestDTO;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;
import urban.urbancompany.Services.FakseStroreProductService;
import urban.urbancompany.Services.iProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    iProductService productService;

    public ProductController(iProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public  List<Product> getAllProducts (){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductByid(id);
    }

    @GetMapping("/products/category")
    public  List<Category> getAllCateogries (){
        return new ArrayList<>();
    }

    @GetMapping("/products/category/cateogryname")
    public  List<Product> getAllProducts(@PathVariable("cateogryname") String cateogryname){
        return new ArrayList<>();
    }

    @PostMapping("/products")
    public  Product addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return new Product();
    }
    //partial update
    @PatchMapping("/products/{id}") //
    public  Product patchsomeDetailsInProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO productRequestDTO ){
        return new Product();
    }
    // complete replacing the product details
    @PutMapping("/products/{id}") //
    public  Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO productRequestDTO ){
        return new Product();
    }

    @DeleteMapping("/products/{id}")
    public  boolean deleteProductById(@PathVariable Long id){
        return true;
    }

}
