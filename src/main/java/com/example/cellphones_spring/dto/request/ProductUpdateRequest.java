package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private String name;
    private String slug;
    private String sku;
    private Long categoryId;
    private Long brandId;
    private String shortDescription;
    private String fullDescription;
    private BigDecimal price;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private BigDecimal weight;
    private String dimensions;
    private Integer warrantyPeriod;
    private Boolean isFeatured;
    private ProductStatus status;
    private String groupName;
    private String metaTitle;
    private String metaDescription;
}
