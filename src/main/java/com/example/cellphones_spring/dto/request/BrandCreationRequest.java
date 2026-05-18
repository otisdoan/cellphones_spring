package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandCreationRequest {
    @NotBlank(message = "Brand name is required")
    private String name;
    
    @NotBlank(message = "Brand slug is required")
    private String slug;
    
    private String logoUrl;
    private String description;
    private Boolean isActive;
}
