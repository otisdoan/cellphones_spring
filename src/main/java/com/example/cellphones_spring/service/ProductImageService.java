package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ProductImageCreationRequest;
import com.example.cellphones_spring.dto.request.ProductImageUpdateRequest;
import com.example.cellphones_spring.dto.response.ProductImageResponse;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductImage;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ProductImageMapper;
import com.example.cellphones_spring.repository.ProductImageRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ProductImageMapper productImageMapper;

    public List<ProductImageResponse> getAll() {
        return productImageRepository.findAll().stream()
                .map(productImageMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductImageResponse getById(Long id) {
        ProductImage image = productImageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_IMAGE_NOT_EXISTED));
        return productImageMapper.toResponse(image);
    }

    @Transactional
    public ProductImageResponse create(ProductImageCreationRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(request.getImageUrl())
                .altText(request.getAltText())
                .sortOrder(request.getSortOrder())
                .isPrimary(request.getIsPrimary() != null ? request.getIsPrimary() : false)
                .build();

        image = productImageRepository.save(image);
        return productImageMapper.toResponse(image);
    }

    @Transactional
    public ProductImageResponse update(Long id, ProductImageUpdateRequest request) {
        ProductImage image = productImageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_IMAGE_NOT_EXISTED));

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            image.setProduct(product);
        }

        if (request.getImageUrl() != null) image.setImageUrl(request.getImageUrl());
        if (request.getAltText() != null) image.setAltText(request.getAltText());
        if (request.getSortOrder() != null) image.setSortOrder(request.getSortOrder());
        if (request.getIsPrimary() != null) image.setIsPrimary(request.getIsPrimary());

        image = productImageRepository.save(image);
        return productImageMapper.toResponse(image);
    }

    @Transactional
    public void delete(Long id) {
        if (!productImageRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_IMAGE_NOT_EXISTED);
        }
        productImageRepository.deleteById(id);
    }
}
