package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.ProductAttributeResponse;
import com.example.cellphones_spring.entity.ProductAttribute;
import org.springframework.stereotype.Component;

@Component
public class ProductAttributeMapper {

    public ProductAttributeResponse toResponse(ProductAttribute attribute) {
        if (attribute == null) {
            return null;
        }

        return ProductAttributeResponse.builder()
                .id(attribute.getId())
                .productId(attribute.getProduct() != null ? attribute.getProduct().getId() : null)
                .attributeName(attribute.getAttributeName())
                .attributeValue(attribute.getAttributeValue())
                .attributeGroup(attribute.getAttributeGroup())
                .sortOrder(attribute.getSortOrder())
                .build();
    }
}
