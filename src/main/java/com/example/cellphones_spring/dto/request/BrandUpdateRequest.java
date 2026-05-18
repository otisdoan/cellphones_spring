package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class BrandUpdateRequest {
    private String name;
    private String slug;
    private String logoUrl;
    private String description;
    private Boolean isActive;
}
