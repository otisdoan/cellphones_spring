package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.ProductResponse;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductImage;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .sku(product.getSku())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .brandId(product.getBrand() != null ? product.getBrand().getId() : null)
                .shortDescription(product.getShortDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .costPrice(product.getCostPrice())
                .weight(product.getWeight())
                .dimensions(product.getDimensions())
                .warrantyPeriod(product.getWarrantyPeriod())
                .isFeatured(product.getIsFeatured())
                .status(product.getStatus())
                .ratingAverage(product.getRatingAverage())
                .ratingCount(product.getRatingCount())
                .groupName(product.getGroupName())
                .metaTitle(product.getMetaTitle())
                .metaDescription(product.getMetaDescription())
                .productImage(product.getImages() != null ?
                        product.getImages().stream()
                                .map(ProductImage::getImageUrl)
                                .collect(Collectors.toList()) : List.of())
                .build();
    }
}
