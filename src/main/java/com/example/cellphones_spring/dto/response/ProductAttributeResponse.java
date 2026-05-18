package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAttributeResponse {
    private Long id;
    private Long productId;
    private String attributeName;
    private String attributeValue;
    private String attributeGroup;
    private Integer sortOrder;
}
