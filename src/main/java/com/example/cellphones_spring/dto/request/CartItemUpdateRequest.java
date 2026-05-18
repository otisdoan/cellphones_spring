package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartItemUpdateRequest {
    private Long userId;
    private Long productId;
    private Long variantId;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
