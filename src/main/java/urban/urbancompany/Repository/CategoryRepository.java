package urban.urbancompany.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urban.urbancompany.Models.Product;

@Repository
public interface CategoryRepository extends JpaRepository {

    public <S extends Product> S save(S entity);
}
