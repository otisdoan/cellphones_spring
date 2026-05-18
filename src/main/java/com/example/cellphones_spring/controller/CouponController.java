package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.CouponCreationRequest;
import com.example.cellphones_spring.dto.request.CouponUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.CouponResponse;
import com.example.cellphones_spring.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.COUPONS)
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CouponResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(couponService.getAll(), "Get all coupons successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CouponResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(couponService.getById(id), "Get coupon by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CouponResponse>> create(@Valid @RequestBody CouponCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(couponService.create(request), "Create coupon successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CouponResponse>> update(@PathVariable Long id, @Valid @RequestBody CouponUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(couponService.update(id, request), "Update coupon successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        couponService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete coupon successfully"));
    }
}
