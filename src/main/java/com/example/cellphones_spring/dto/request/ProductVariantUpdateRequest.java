package com.example.cellphones_spring.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductVariantUpdateRequest {
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
