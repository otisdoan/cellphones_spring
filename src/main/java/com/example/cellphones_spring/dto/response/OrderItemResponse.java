package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private Long productId;
    private Long variantId;
    private String productName;
    private String variantName;
    private String sku;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total;
    private BigDecimal salePrice;
    private String imageUrl;
    private Long stockQuantity;
}
