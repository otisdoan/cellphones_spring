package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ProductCreationRequest;
import com.example.cellphones_spring.dto.request.ProductUpdateRequest;
import com.example.cellphones_spring.dto.response.ProductResponse;
import com.example.cellphones_spring.entity.Brand;
import com.example.cellphones_spring.entity.Category;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.enums.ProductStatus;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ProductMapper;
import com.example.cellphones_spring.repository.BrandRepository;
import com.example.cellphones_spring.repository.CategoryRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return productMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse create(ProductCreationRequest request) {
        if (productRepository.existsBySlug(request.getSlug())) {
            throw new AppException(ErrorCode.PRODUCT_SLUG_EXISTED);
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        Brand brand = null;
        if (request.getBrandId() != null) {
            brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        }

        Product product = Product.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .sku(request.getSku())
                .category(category)
                .brand(brand)
                .shortDescription(request.getShortDescription())
                .fullDescription(request.getFullDescription())
                .price(request.getPrice())
                .salePrice(request.getSalePrice())
                .costPrice(request.getCostPrice())
                .weight(request.getWeight())
                .dimensions(request.getDimensions())
                .warrantyPeriod(request.getWarrantyPeriod() != null ? request.getWarrantyPeriod() : 12)
                .isFeatured(request.getIsFeatured() != null ? request.getIsFeatured() : false)
                .status(request.getStatus() != null ? request.getStatus() : ProductStatus.ACTIVE)
                .groupName(request.getGroupName())
                .metaTitle(request.getMetaTitle())
                .metaDescription(request.getMetaDescription())
                .build();

        product = productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        if (request.getSlug() != null && !request.getSlug().equals(product.getSlug())) {
            if (productRepository.existsBySlug(request.getSlug())) {
                throw new AppException(ErrorCode.PRODUCT_SLUG_EXISTED);
            }
            product.setSlug(request.getSlug());
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
            product.setCategory(category);
        }

        if (request.getBrandId() != null) {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
            product.setBrand(brand);
        }

        if (request.getName() != null) product.setName(request.getName());
        if (request.getSku() != null) product.setSku(request.getSku());
        if (request.getShortDescription() != null) product.setShortDescription(request.getShortDescription());
        if (request.getFullDescription() != null) product.setFullDescription(request.getFullDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getSalePrice() != null) product.setSalePrice(request.getSalePrice());
        if (request.getCostPrice() != null) product.setCostPrice(request.getCostPrice());
        if (request.getWeight() != null) product.setWeight(request.getWeight());
        if (request.getDimensions() != null) product.setDimensions(request.getDimensions());
        if (request.getWarrantyPeriod() != null) product.setWarrantyPeriod(request.getWarrantyPeriod());
        if (request.getIsFeatured() != null) product.setIsFeatured(request.getIsFeatured());
        if (request.getStatus() != null) product.setStatus(request.getStatus());
        if (request.getGroupName() != null) product.setGroupName(request.getGroupName());
        if (request.getMetaTitle() != null) product.setMetaTitle(request.getMetaTitle());
        if (request.getMetaDescription() != null) product.setMetaDescription(request.getMetaDescription());

        product = productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        productRepository.deleteById(id);
    }
}
