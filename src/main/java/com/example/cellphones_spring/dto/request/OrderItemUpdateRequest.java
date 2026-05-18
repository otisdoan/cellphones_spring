package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemUpdateRequest {
    private Long orderId;
    private Long productId;
    private Long variantId;
    private String productName;
    private String variantName;
    private String sku;
    private BigDecimal price;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    private BigDecimal total;
    private BigDecimal salePrice;
    private String imageUrl;
    private Long stockQuantity;
}
