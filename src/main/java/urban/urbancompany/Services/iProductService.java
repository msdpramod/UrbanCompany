package urban.urbancompany.Services;

import urban.urbancompany.Models.Product;

import java.util.List;

public interface iProductService {
      Product getProductByid(Long id);
      List<Product> getAllProducts();
}
