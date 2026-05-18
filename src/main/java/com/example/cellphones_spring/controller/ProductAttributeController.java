package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.ProductAttributeCreationRequest;
import com.example.cellphones_spring.dto.request.ProductAttributeUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.ProductAttributeResponse;
import com.example.cellphones_spring.service.ProductAttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.PRODUCT_ATTRIBUTES)
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductAttributeResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(productAttributeService.getAll(), "Get all product attributes successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductAttributeResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productAttributeService.getById(id), "Get product attribute by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductAttributeResponse>> create(@Valid @RequestBody ProductAttributeCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productAttributeService.create(request), "Create product attribute successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductAttributeResponse>> update(@PathVariable Long id, @Valid @RequestBody ProductAttributeUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productAttributeService.update(id, request), "Update product attribute successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productAttributeService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete product attribute successfully"));
    }
}
