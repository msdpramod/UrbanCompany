package group.urbancompany.Repository;

import group.urbancompany.Models.CartItem;
import group.urbancompany.Models.Product;
import group.urbancompany.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByUserAndProduct(User user, Product product);
}
