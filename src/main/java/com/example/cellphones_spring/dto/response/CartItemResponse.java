package com.example.cellphones_spring.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private Long id;
    
    @JsonProperty("user_id")
    private Long userId;
    
    @JsonProperty("product_id")
    private Long productId;
    
    @JsonProperty("variant_id")
    private Long variantId;
    
    private Integer quantity;
}
