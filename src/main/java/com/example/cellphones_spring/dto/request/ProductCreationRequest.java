package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreationRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Slug is required")
    private String slug;
    
    private String sku;
    
    @NotNull(message = "Category is required")
    private Long categoryId;
    
    private Long brandId;
    private String shortDescription;
    private String fullDescription;
    
    @NotNull(message = "Price is required")
    private BigDecimal price;
    
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private BigDecimal weight;
    private String dimensions;
    private Integer warrantyPeriod;
    private Boolean isFeatured;
    private ProductStatus status;
    
    @NotBlank(message = "Group name is required")
    private String groupName;
    
    private String metaTitle;
    private String metaDescription;
}
