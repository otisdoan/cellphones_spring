package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductVariantCreationRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    @NotBlank(message = "Variant name is required")
    private String variantName;
    
    @NotBlank(message = "SKU is required")
    private String sku;
    
    @NotNull(message = "Price is required")
    private BigDecimal price;
    
    private BigDecimal salePrice;
    private BigDecimal stockQuantity;
    private String imageUrl;
    private Boolean isActive;
    private String capacity;
}
