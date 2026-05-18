package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class ProductAttributeUpdateRequest {
    private Long productId;
    private String attributeName;
    private String attributeValue;
    private String attributeGroup;
    private Integer sortOrder;
}
