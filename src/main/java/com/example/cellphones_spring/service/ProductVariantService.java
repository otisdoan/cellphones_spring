package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ProductVariantCreationRequest;
import com.example.cellphones_spring.dto.request.ProductVariantUpdateRequest;
import com.example.cellphones_spring.dto.response.ProductVariantCapacityResponse;
import com.example.cellphones_spring.dto.response.ProductVariantResponse;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductVariant;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ProductVariantMapper;
import com.example.cellphones_spring.repository.ProductRepository;
import com.example.cellphones_spring.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProductVariantMapper productVariantMapper;

    public List<ProductVariantResponse> getAll() {
        return productVariantRepository.findAll().stream()
                .map(productVariantMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductVariantResponse getById(Long id) {
        ProductVariant variant = productVariantRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
        return productVariantMapper.toResponse(variant);
    }

    @Transactional
    public ProductVariantResponse create(ProductVariantCreationRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ProductVariant variant = ProductVariant.builder()
                .product(product)
                .variantName(request.getVariantName())
                .sku(request.getSku())
                .price(request.getPrice())
                .salePrice(request.getSalePrice())
                .stockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : BigDecimal.ZERO)
                .imageUrl(request.getImageUrl())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .capacity(request.getCapacity())
                .build();

        variant = productVariantRepository.save(variant);
        return productVariantMapper.toResponse(variant);
    }

    @Transactional
    public ProductVariantResponse update(Long id, ProductVariantUpdateRequest request) {
        ProductVariant variant = productVariantRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            variant.setProduct(product);
        }

        if (request.getVariantName() != null) variant.setVariantName(request.getVariantName());
        if (request.getSku() != null) variant.setSku(request.getSku());
        if (request.getPrice() != null) variant.setPrice(request.getPrice());
        if (request.getSalePrice() != null) variant.setSalePrice(request.getSalePrice());
        if (request.getStockQuantity() != null) variant.setStockQuantity(request.getStockQuantity());
        if (request.getImageUrl() != null) variant.setImageUrl(request.getImageUrl());
        if (request.getIsActive() != null) variant.setIsActive(request.getIsActive());
        if (request.getCapacity() != null) variant.setCapacity(request.getCapacity());

        variant = productVariantRepository.save(variant);
        return productVariantMapper.toResponse(variant);
    }

    @Transactional
    public void delete(Long id) {
        if (!productVariantRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED);
        }
        productVariantRepository.deleteById(id);
    }

    @Transactional
    public ProductVariantCapacityResponse getCapacityByGroupName(String group_name){
        List<String> capacityList = productVariantRepository.findCapacityByGroupName(group_name)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_CAPACITY_EXCEEDED));
        return ProductVariantCapacityResponse.builder()
                .capacity(capacityList)
                .build();
    }

    @Transactional
    public List<ProductVariantResponse> getVariantByCapacity(String capacity, String group_name){
        var variant = productVariantRepository.findVariantByCapacity(capacity, group_name)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_CAPACITY_EXCEEDED));

        return variant.stream()
                .map(productVariantMapper::toResponse)
                .collect(Collectors.toList());
    }
}
