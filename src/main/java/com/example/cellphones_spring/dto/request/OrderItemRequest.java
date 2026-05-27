package com.example.cellphones_spring.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("variant_id")
    private Long variantId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("variant_name")
    private String variantName;

    private String sku;
    private BigDecimal price;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    private Integer quantity;

    @JsonProperty("image_url")
    private String imageUrl;
}
