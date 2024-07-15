package urban.urbancompany.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urban.urbancompany.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// This is a marker interface
	// No need to write any code inside this interface
	// Spring Data JPA will provide implementation for this
	// We will be using this interface to perform CRUD operations on Product entity

    @Override
    public <S extends Product> S save(S entity);
}
