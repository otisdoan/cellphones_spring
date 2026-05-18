package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductVariantResponse {
    private Long id;
    private Long productId;
    private String variantName;
    private String sku;
    private BigDecimal price;
    private BigDecimal salePrice;
    private BigDecimal stockQuantity;
    private String imageUrl;
    private Boolean isActive;
    private String capacity;
}
