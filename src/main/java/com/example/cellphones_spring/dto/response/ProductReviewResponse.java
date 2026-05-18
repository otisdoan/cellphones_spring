package com.example.cellphones_spring.dto.response;

import com.example.cellphones_spring.enums.ReviewStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReviewResponse {
    private Long id;
    private Long productId;
    private Long userId;
    private Long orderId;
    private Integer rating;
    private String title;
    private String content;
    private String images;
    private Boolean isVerifiedPurchase;
    private ReviewStatus status;
    private Integer helpfulCount;
}
