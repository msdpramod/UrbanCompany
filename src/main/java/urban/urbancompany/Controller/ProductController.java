package urban.urbancompany.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urban.urbancompany.DTOs.RequestDTO;
import urban.urbancompany.Models.Category;
import urban.urbancompany.Models.Product;
import urban.urbancompany.Services.iProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final iProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") iProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        //if u want to handle error handle here itself else if you want to handle error globally then use @ControllerAdvice
        ResponseEntity responseEntity;
        try{
            List<Product> products = productService.getAllProducts();
            responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Error retrieving products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = null;
        try {
            product = productService.getProductById(id);
        } catch (urban.urbancompany.Excepections.ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        ResponseEntity responseEntity;
        try {
            List<Category> categories = productService.getAllCategories();
            responseEntity = new ResponseEntity<>(categories, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error retrieving categories: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/categories/{catName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("catName") String categoryName){
        ResponseEntity responseEntity;
        try {
            List<Product> products = productService.getProductsByCategory(categoryName);
            responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error retrieving products by category: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody RequestDTO requestDTO){
        ResponseEntity responseEntity;
        try {
            Product newProduct = productService.addProduct(requestDTO);
            responseEntity = new ResponseEntity<>(newProduct, HttpStatus.CREATED);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error adding product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody RequestDTO requestDTO){
        ResponseEntity responseEntity;
        try {
            Product updatedProduct = productService.updateProduct(id, requestDTO);
            responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error updating product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> replaceEntireProduct(@PathVariable Long id, @RequestBody RequestDTO requestDTO){
        ResponseEntity responseEntity;
        try {
            Product updatedProduct = productService.replaceEntireProduct(id, requestDTO);
            responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error replacing product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id){
        ResponseEntity responseEntity;
        try {
            Boolean isDeleted = productService.deleteProduct(id);
            responseEntity = new ResponseEntity<>(isDeleted, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error deleting product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
