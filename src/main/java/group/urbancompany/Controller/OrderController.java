package group.urbancompany.Controller;

import group.urbancompany.DTOs.OrderResponse;
import group.urbancompany.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") String userId) {
        OrderResponse orderResponse = orderService.createOrder(userId);

        if (orderResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new OrderResponse(null, "FAILED", "Could not create order. Cart may be empty or user not found.", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
