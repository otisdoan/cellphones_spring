package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.ProductImageCreationRequest;
import com.example.cellphones_spring.dto.request.ProductImageUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.ProductImageResponse;
import com.example.cellphones_spring.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.PRODUCT_IMAGES)
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductImageResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productImageService.getAll(), "Get all product images successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImageResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productImageService.getById(id), "Get product image by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductImageResponse>> create(@Valid @RequestBody ProductImageCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productImageService.create(request), "Create product image successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImageResponse>> update(@PathVariable Long id, @Valid @RequestBody ProductImageUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productImageService.update(id, request), "Update product image successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productImageService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete product image successfully"));
    }
}
