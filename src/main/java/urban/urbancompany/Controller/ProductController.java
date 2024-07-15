package urban.urbancompany.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urban.urbancompany.DTOs.ErrorResponseDTO;
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

    private final iProductService productService;

    public ProductController(iProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public  ResponseEntity<List<Product>> getAllProducts (){
//        ResponseEntity responseEntity;
//        try{
//            List<Product> products= productService.getAllProducts();
//            responseEntity= new ResponseEntity(products, HttpStatus.OK);
//        }catch (Exception e){
//            ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO();
//            errorResponseDTO.setMessage("something went wrong");
//            responseEntity= new ResponseEntity(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        //here we can add more catch blocks for different exceptions
//        //handling different exceptions in different catch blocks is not a good practice
//        return responseEntity;

        List<Product> products= productService.getAllProducts();
        ResponseEntity responseEntity= new ResponseEntity(products, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product= productService.getProductByid(id);
        ResponseEntity responseEntity= new ResponseEntity(product, HttpStatus.OK);
        return responseEntity;
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
    public  ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        Product product= productService.addProduct(productRequestDTO);
        ResponseEntity responseEntity= new ResponseEntity(product, HttpStatus.CREATED);
        return responseEntity;
    }
    //partial update
    @PatchMapping("/products/{id}") //
    public  ResponseEntity<Product> patchsomeDetailsInProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO productRequestDTO ){
        Product product= productService.patchsomeDetailsInProduct(id, productRequestDTO);
        ResponseEntity responseEntity= new ResponseEntity(product, HttpStatus.OK);
        return responseEntity;

    }
    // complete replacing the product details
    @PutMapping("/products/{id}") //
    public  ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDTO productRequestDTO ){

        Product product= productService.updateProduct(id, productRequestDTO);
        ResponseEntity responseEntity= new ResponseEntity(product, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProductById(@PathVariable Long id){

        return true;
    }

}
