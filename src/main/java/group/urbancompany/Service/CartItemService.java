package group.urbancompany.Service;

import group.urbancompany.DTOs.CartItemRequest;
import group.urbancompany.Models.CartItem;
import group.urbancompany.Models.Product;
import group.urbancompany.Models.User;
import group.urbancompany.Repository.CartItemRepository;
import group.urbancompany.Repository.ProductRepository;
import group.urbancompany.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public boolean addToCart(String id, CartItemRequest cartItemRequest) {
        Optional<Product> product = productRepository.findById(cartItemRequest.getProductId());
        if(product.isEmpty()) {
           return false;
        }

        Product productObject = product.get();
        if(productObject.getStockQuantity() < cartItemRequest.getQuantity()) {
            return false;
        }

        Optional<User> user = userRepository.findById(Long.parseLong(id));
        if(user.isEmpty()) {
            return false;

        }
        User userObject = user.get();

        // Logic to add item to cart goes here

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(userObject, productObject);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            productObject.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity()));
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(userObject);
            cartItem.setProduct(productObject);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(productObject.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }

        return true;
    }

}
