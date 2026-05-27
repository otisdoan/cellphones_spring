package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.CartItemCreationRequest;
import com.example.cellphones_spring.dto.request.CartItemUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.CartItemResponse;
import com.example.cellphones_spring.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.CART_ITEMS)
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(cartItemService.getAll(), "Get all cart items successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CartItemResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(cartItemService.getById(id), "Get cart item by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CartItemResponse>> create(@Valid @RequestBody CartItemCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(cartItemService.create(request), "Create cart item successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CartItemResponse>> update(@PathVariable Long id, @Valid @RequestBody CartItemUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(cartItemService.update(id, request), "Update cart item successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        cartItemService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete cart item successfully"));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(cartItemService.getByUserId(userId), "Get cart items by user id successfully"));
    }
}
