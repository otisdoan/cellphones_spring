package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.BrandResponse;
import com.example.cellphones_spring.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandResponse toResponse(Brand brand) {
        if (brand == null) {
            return null;
        }

        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .slug(brand.getSlug())
                .logoUrl(brand.getLogoUrl())
                .description(brand.getDescription())
                .isActive(brand.getIsActive())
                .build();
    }
}
