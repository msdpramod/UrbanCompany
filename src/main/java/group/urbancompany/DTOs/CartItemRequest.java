package group.urbancompany.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CartItemRequest {
    private Long productId;
    private Integer quantity;
}
