package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class CategoryUpdateRequest {
    private String name;
    private String slug;
    private String description;
    private Long parentId;
    private String imageUrl;
    private Integer sortOrder;
    private Boolean isActive;
}
