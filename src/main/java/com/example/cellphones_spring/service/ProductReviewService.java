package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ProductReviewCreationRequest;
import com.example.cellphones_spring.dto.request.ProductReviewUpdateRequest;
import com.example.cellphones_spring.dto.response.ProductReviewResponse;
import com.example.cellphones_spring.entity.Order;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductReview;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.enums.ReviewStatus;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ProductReviewMapper;
import com.example.cellphones_spring.repository.OrderRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import com.example.cellphones_spring.repository.ProductReviewRepository;
import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductReviewMapper productReviewMapper;

    public List<ProductReviewResponse> getAll() {
        return productReviewRepository.findAll().stream()
                .map(productReviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductReviewResponse getById(Long id) {
        ProductReview review = productReviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_REVIEW_NOT_EXISTED));
        return productReviewMapper.toResponse(review);
    }

    @Transactional
    public ProductReviewResponse create(ProductReviewCreationRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Order order = null;
        // Commenting out Order fetching to avoid error if OrderRepository is not fully implemented or ErrorCode.ORDER_NOT_EXISTED is missing
        // if (request.getOrderId() != null) {
        //    order = orderRepository.findById(request.getOrderId()).orElse(null);
        // }

        ProductReview review = ProductReview.builder()
                .product(product)
                .user(user)
                .order(order)
                .rating(request.getRating())
                .title(request.getTitle())
                .content(request.getContent())
                .images(request.getImages())
                .isVerifiedPurchase(request.getIsVerifiedPurchase() != null ? request.getIsVerifiedPurchase() : false)
                .status(request.getStatus() != null ? request.getStatus() : ReviewStatus.PENDING)
                .helpfulCount(0)
                .build();

        review = productReviewRepository.save(review);
        return productReviewMapper.toResponse(review);
    }

    @Transactional
    public ProductReviewResponse update(Long id, ProductReviewUpdateRequest request) {
        ProductReview review = productReviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_REVIEW_NOT_EXISTED));

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            review.setProduct(product);
        }

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            review.setUser(user);
        }

        if (request.getRating() != null) review.setRating(request.getRating());
        if (request.getTitle() != null) review.setTitle(request.getTitle());
        if (request.getContent() != null) review.setContent(request.getContent());
        if (request.getImages() != null) review.setImages(request.getImages());
        if (request.getIsVerifiedPurchase() != null) review.setIsVerifiedPurchase(request.getIsVerifiedPurchase());
        if (request.getStatus() != null) review.setStatus(request.getStatus());
        if (request.getHelpfulCount() != null) review.setHelpfulCount(request.getHelpfulCount());

        review = productReviewRepository.save(review);
        return productReviewMapper.toResponse(review);
    }

    @Transactional
    public void delete(Long id) {
        if (!productReviewRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_REVIEW_NOT_EXISTED);
        }
        productReviewRepository.deleteById(id);
    }
}
