package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.UserAddressCreationRequest;
import com.example.cellphones_spring.dto.request.UserAddressUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.UserAddressResponse;
import com.example.cellphones_spring.service.UserAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.USER_ADDRESSES)
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserAddressResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(userAddressService.getAll(), "Get all user addresses successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserAddressResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userAddressService.getById(id), "Get user address by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserAddressResponse>> create(@Valid @RequestBody UserAddressCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(userAddressService.create(request), "Create user address successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserAddressResponse>> update(@PathVariable Long id, @Valid @RequestBody UserAddressUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(userAddressService.update(id, request), "Update user address successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userAddressService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete user address successfully"));
    }
}
