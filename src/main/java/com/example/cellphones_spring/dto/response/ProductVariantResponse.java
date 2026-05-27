package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
public class ProductVariantResponse {

    private Long id;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("variant_name")
    private String variantName;

    private String sku;

    private BigDecimal price;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @JsonProperty("stock_quantity")
    private BigDecimal stockQuantity;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("is_active")
    private Boolean isActive;

    private String capacity;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("variant_id")
    private Long variantId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_slug")
    private String productSlug;

    private Integer quantity;

    private BigDecimal total;

}
