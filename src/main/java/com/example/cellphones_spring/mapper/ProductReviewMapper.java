package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.ProductReviewResponse;
import com.example.cellphones_spring.entity.ProductReview;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMapper {

    public ProductReviewResponse toResponse(ProductReview review) {
        if (review == null) {
            return null;
        }

        return ProductReviewResponse.builder()
                .id(review.getId())
                .productId(review.getProduct() != null ? review.getProduct().getId() : null)
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .orderId(review.getOrder() != null ? review.getOrder().getId() : null)
                .rating(review.getRating())
                .title(review.getTitle())
                .content(review.getContent())
                .images(review.getImages())
                .isVerifiedPurchase(review.getIsVerifiedPurchase())
                .status(review.getStatus())
                .helpfulCount(review.getHelpfulCount())
                .build();
    }
}
