package group.urbancompany.Service;

import group.urbancompany.DTOs.OrderItemDTO;
import group.urbancompany.DTOs.OrderResponse;
import group.urbancompany.Models.*;
import group.urbancompany.Repository.OrderRepository;
import group.urbancompany.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final CartItemService cartItemService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderService(CartItemService cartItemService,
                        UserRepository userRepository,
                        OrderRepository orderRepository) {
        this.cartItemService = cartItemService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(String userId) {
        // Validate user and cart items
        List<CartItem> cartItems = cartItemService.getCartItems(userId);
        if (cartItems.isEmpty()) {
            return null;
        }

        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();

        // Calculate total
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            totalAmount = totalAmount.add(cartItem.getPrice());
        }

        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setTotal(totalAmount);
        order.setStatus(OrderStatus.CONFIRMED);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cartItemService.clearCart(userId);

        return mapToOrderResponse(savedOrder);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();

        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(
                    orderItem.getId(),
                    orderItem.getProduct().getId(),
                    orderItem.getQuantity(),
                    orderItem.getPrice(),
                    orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
            );
            orderItemDTOs.add(orderItemDTO);
        }

        return new OrderResponse(
                order.getId(),
                order.getStatus().toString(),
                "Order placed successfully",
                orderItemDTOs
        );
    }
}
