package urban.urbancompany.Repository;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urban.urbancompany.Models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CateogryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    Optional<Category> findByName(String categoryName);

    Category save(Category category);
}
