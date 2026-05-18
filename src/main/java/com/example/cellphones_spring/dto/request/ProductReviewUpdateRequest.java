package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.ReviewStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductReviewUpdateRequest {
    private Long productId;
    private Long userId;
    private Long orderId;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;
    
    private String title;
    private String content;
    private String images;
    private Boolean isVerifiedPurchase;
    private ReviewStatus status;
    private Integer helpfulCount;
}
