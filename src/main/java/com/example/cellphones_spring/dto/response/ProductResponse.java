package com.example.cellphones_spring.dto.response;

import com.example.cellphones_spring.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductResponse {
    private Long id;

    private String name;

    private String slug;

    private String sku;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("full_description")
    private String fullDescription;

    private BigDecimal price;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @JsonProperty("cost_price")
    private BigDecimal costPrice;

    private BigDecimal weight;

    private String dimensions;

    @JsonProperty("warranty_period")
    private Integer warrantyPeriod;

    @JsonProperty("is_featured")
    private Boolean isFeatured;

    private ProductStatus status;

    @JsonProperty("rating_average")
    private BigDecimal ratingAverage;

    @JsonProperty("rating_count")
    private Integer ratingCount;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("meta_title")
    private String metaTitle;

    @JsonProperty("meta_description")
    private String metaDescription;

    @JsonProperty("product_image")
    private List<String> productImage;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("brand_name")
    private String brandName;
}
