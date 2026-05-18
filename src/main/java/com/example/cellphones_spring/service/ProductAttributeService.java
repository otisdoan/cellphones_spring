package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ProductAttributeCreationRequest;
import com.example.cellphones_spring.dto.request.ProductAttributeUpdateRequest;
import com.example.cellphones_spring.dto.response.ProductAttributeResponse;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductAttribute;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ProductAttributeMapper;
import com.example.cellphones_spring.repository.ProductAttributeRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;
    private final ProductRepository productRepository;
    private final ProductAttributeMapper productAttributeMapper;

    public List<ProductAttributeResponse> getAll() {
        return productAttributeRepository.findAll().stream()
                .map(productAttributeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductAttributeResponse getById(Long id) {
        ProductAttribute attribute = productAttributeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ATTRIBUTE_NOT_EXISTED));
        return productAttributeMapper.toResponse(attribute);
    }

    @Transactional
    public ProductAttributeResponse create(ProductAttributeCreationRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ProductAttribute attribute = ProductAttribute.builder()
                .product(product)
                .attributeName(request.getAttributeName())
                .attributeValue(request.getAttributeValue())
                .attributeGroup(request.getAttributeGroup())
                .sortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0)
                .build();

        attribute = productAttributeRepository.save(attribute);
        return productAttributeMapper.toResponse(attribute);
    }

    @Transactional
    public ProductAttributeResponse update(Long id, ProductAttributeUpdateRequest request) {
        ProductAttribute attribute = productAttributeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ATTRIBUTE_NOT_EXISTED));

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            attribute.setProduct(product);
        }

        if (request.getAttributeName() != null) attribute.setAttributeName(request.getAttributeName());
        if (request.getAttributeValue() != null) attribute.setAttributeValue(request.getAttributeValue());
        if (request.getAttributeGroup() != null) attribute.setAttributeGroup(request.getAttributeGroup());
        if (request.getSortOrder() != null) attribute.setSortOrder(request.getSortOrder());

        attribute = productAttributeRepository.save(attribute);
        return productAttributeMapper.toResponse(attribute);
    }

    @Transactional
    public void delete(Long id) {
        if (!productAttributeRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_ATTRIBUTE_NOT_EXISTED);
        }
        productAttributeRepository.deleteById(id);
    }
}
