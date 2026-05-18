package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.ReviewStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductReviewCreationRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private Long orderId;
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;
    
    private String title;
    private String content;
    private String images;
    private Boolean isVerifiedPurchase;
    private ReviewStatus status;
}
