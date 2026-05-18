package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class ProductImageUpdateRequest {
    private Long productId;
    private String imageUrl;
    private String altText;
    private Integer sortOrder;
    private Boolean isPrimary;
}
