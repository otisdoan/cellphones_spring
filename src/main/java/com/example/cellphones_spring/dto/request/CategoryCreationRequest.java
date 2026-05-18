package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreationRequest {
    @NotBlank(message = "Category name is required")
    private String name;
    
    @NotBlank(message = "Category slug is required")
    private String slug;
    
    private String description;
    private Long parentId;
    private String imageUrl;
    private Integer sortOrder;
    private Boolean isActive;
}
