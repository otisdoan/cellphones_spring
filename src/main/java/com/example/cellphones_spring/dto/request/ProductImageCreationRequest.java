package com.example.cellphones_spring.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImageCreationRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    private String altText;
    private Integer sortOrder;
    private Boolean isPrimary;
}
