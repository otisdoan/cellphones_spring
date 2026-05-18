package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.ProductReviewCreationRequest;
import com.example.cellphones_spring.dto.request.ProductReviewUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.ProductReviewResponse;
import com.example.cellphones_spring.service.ProductReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.PRODUCT_REVIEWS)
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductReviewResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productReviewService.getAll(), "Get all product reviews successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductReviewResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productReviewService.getById(id), "Get product review by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductReviewResponse>> create(@Valid @RequestBody ProductReviewCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productReviewService.create(request), "Create product review successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductReviewResponse>> update(@PathVariable Long id, @Valid @RequestBody ProductReviewUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productReviewService.update(id, request), "Update product review successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productReviewService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete product review successfully"));
    }
}
