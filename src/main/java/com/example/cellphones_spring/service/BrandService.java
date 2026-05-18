package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.BrandCreationRequest;
import com.example.cellphones_spring.dto.request.BrandUpdateRequest;
import com.example.cellphones_spring.dto.response.BrandResponse;
import com.example.cellphones_spring.entity.Brand;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.BrandMapper;
import com.example.cellphones_spring.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandResponse> getAll() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BrandResponse getById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        return brandMapper.toResponse(brand);
    }

    @Transactional
    public BrandResponse create(BrandCreationRequest request) {
        if (brandRepository.existsBySlug(request.getSlug())) {
            throw new AppException(ErrorCode.BRAND_SLUG_EXISTED);
        }

        Brand brand = Brand.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .logoUrl(request.getLogoUrl())
                .description(request.getDescription())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        brand = brandRepository.save(brand);
        return brandMapper.toResponse(brand);
    }

    @Transactional
    public BrandResponse update(Long id, BrandUpdateRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));

        if (request.getSlug() != null && !request.getSlug().equals(brand.getSlug())) {
            if (brandRepository.existsBySlug(request.getSlug())) {
                throw new AppException(ErrorCode.BRAND_SLUG_EXISTED);
            }
            brand.setSlug(request.getSlug());
        }

        if (request.getName() != null) brand.setName(request.getName());
        if (request.getLogoUrl() != null) brand.setLogoUrl(request.getLogoUrl());
        if (request.getDescription() != null) brand.setDescription(request.getDescription());
        if (request.getIsActive() != null) brand.setIsActive(request.getIsActive());

        brand = brandRepository.save(brand);
        return brandMapper.toResponse(brand);
    }

    @Transactional
    public void delete(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new AppException(ErrorCode.BRAND_NOT_EXISTED);
        }
        brandRepository.deleteById(id);
    }
}
