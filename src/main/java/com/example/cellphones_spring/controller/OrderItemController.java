package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.OrderItemCreationRequest;
import com.example.cellphones_spring.dto.request.OrderItemUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.OrderItemResponse;
import com.example.cellphones_spring.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.ORDER_ITEMS)
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderItemResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(orderItemService.getAll(), "Get all order items successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderItemResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(orderItemService.getById(id), "Get order item by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderItemResponse>> create(@Valid @RequestBody OrderItemCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(orderItemService.create(request), "Create order item successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderItemResponse>> update(@PathVariable Long id, @Valid @RequestBody OrderItemUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(orderItemService.update(id, request), "Update order item successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete order item successfully"));
    }
}
