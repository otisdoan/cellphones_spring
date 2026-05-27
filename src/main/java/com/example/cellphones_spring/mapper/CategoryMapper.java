package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.CategoryResponse;
import com.example.cellphones_spring.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .parentId(category.getParent() != null ? category.getParent() : null)
                .imageUrl(category.getImageUrl())
                .sortOrder(category.getSortOrder())
                .isActive(category.getIsActive())
                .build();
    }
}
