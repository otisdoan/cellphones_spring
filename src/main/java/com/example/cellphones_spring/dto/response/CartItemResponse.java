package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private Long variantId;
    private Integer quantity;
}
