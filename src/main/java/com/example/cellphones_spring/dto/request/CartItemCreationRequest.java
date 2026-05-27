package com.example.cellphones_spring.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemCreationRequest {
    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Long userId;
    
    @NotNull(message = "Product ID is required")
    @JsonProperty("product_id")
    private Long productId;
    
    @JsonProperty("variant_id")
    private Long variantId;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
