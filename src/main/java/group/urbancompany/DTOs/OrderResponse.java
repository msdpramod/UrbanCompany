package group.urbancompany.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String status;
    private String message;
    private List<OrderItemDTO> items;
}
