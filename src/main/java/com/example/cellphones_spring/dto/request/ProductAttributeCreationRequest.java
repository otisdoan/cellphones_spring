package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductAttributeCreationRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    @NotBlank(message = "Attribute name is required")
    private String attributeName;
    
    @NotBlank(message = "Attribute value is required")
    private String attributeValue;
    
    private String attributeGroup;
    private Integer sortOrder;
}
