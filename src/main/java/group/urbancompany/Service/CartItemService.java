package group.urbancompany.Service;

import group.urbancompany.DTOs.CartItemRequest;
import group.urbancompany.Models.CartItem;
import group.urbancompany.Models.Product;
import group.urbancompany.Models.User;
import group.urbancompany.Repository.CartItemRepository;
import group.urbancompany.Repository.ProductRepository;
import group.urbancompany.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartItemService(CartItemRepository cartItemRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean addToCart(String id, CartItemRequest cartItemRequest) {
        // Check product
        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());
        if (productOpt.isEmpty()) return false;

        Product product = productOpt.get();

        // Check stock
        if (product.getStockQuantity() < cartItemRequest.getQuantity()) return false;

        // Check user
        Optional<User> userOpt = userRepository.findById(Long.parseLong(id));
        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();

        // Add or update cart item
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + cartItemRequest.getQuantity();
            existingCartItem.setQuantity(newQuantity);
            existingCartItem.setPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(newQuantity))
            );
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity()))
            );
            cartItemRepository.save(newCartItem);
        }

        return true;
    }

    @Transactional
    public boolean removeFromCart(String id, Long productId) {
        // Check user
        Optional<User> userOpt = userRepository.findById(Long.parseLong(id));
        if (userOpt.isEmpty()) return false;
        User user = userOpt.get();

        // Check product
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) return false;
        Product product = productOpt.get();

        // Find and delete cart item
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            cartItemRepository.delete(existingCartItem);
            return true;
        }
        return false;
    }


    public List<CartItem> getCartItems(String userId) {
        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
        if (userOpt.isEmpty()) return List.of();
        User user = userOpt.get();
        List<CartItem> allCartItems = cartItemRepository.findAll();
        List<CartItem> userCartItems = new ArrayList<>();

        for (CartItem cartItem : allCartItems) {
            if (cartItem.getUser().equals(user)) {
                userCartItems.add(cartItem);
            }
        }

        return userCartItems;


    }

    public void clearCart(String userId) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            cartItemRepository.deleteByUser(user);
        }
    }
}
