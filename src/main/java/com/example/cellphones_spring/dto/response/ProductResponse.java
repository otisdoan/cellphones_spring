package com.example.cellphones_spring.dto.response;

import com.example.cellphones_spring.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private Long id;
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
    private BigDecimal ratingAverage;
    private Integer ratingCount;
    private String groupName;
    private String metaTitle;
    private String metaDescription;
}
