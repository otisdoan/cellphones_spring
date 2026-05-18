package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.ProductVariantCreationRequest;
import com.example.cellphones_spring.dto.request.ProductVariantUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.ProductVariantResponse;
import com.example.cellphones_spring.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.PRODUCT_VARIANTS)
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productVariantService.getAll(), "Get all product variants successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productVariantService.getById(id), "Get product variant by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantResponse>> create(@Valid @RequestBody ProductVariantCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productVariantService.create(request), "Create product variant successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> update(@PathVariable Long id, @Valid @RequestBody ProductVariantUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productVariantService.update(id, request), "Update product variant successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productVariantService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete product variant successfully"));
    }
}
