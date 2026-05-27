package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.CategoryCreationRequest;
import com.example.cellphones_spring.dto.request.CategoryUpdateRequest;
import com.example.cellphones_spring.dto.response.CategoryResponse;
import com.example.cellphones_spring.entity.Category;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.CategoryMapper;
import com.example.cellphones_spring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return categoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryResponse create(CategoryCreationRequest request) {
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new AppException(ErrorCode.CATEGORY_SLUG_EXISTED);
        }

        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        }

        Category category = Category.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .description(request.getDescription())
                .parent(parent.getParent())
                .imageUrl(request.getImageUrl())
                .sortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0)
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        if (request.getSlug() != null && !request.getSlug().equals(category.getSlug())) {
            if (categoryRepository.existsBySlug(request.getSlug())) {
                throw new AppException(ErrorCode.CATEGORY_SLUG_EXISTED);
            }
            category.setSlug(request.getSlug());
        }

        if (request.getName() != null)
            category.setName(request.getName());
        if (request.getDescription() != null)
            category.setDescription(request.getDescription());
        if (request.getImageUrl() != null)
            category.setImageUrl(request.getImageUrl());
        if (request.getSortOrder() != null)
            category.setSortOrder(request.getSortOrder());
        if (request.getIsActive() != null)
            category.setIsActive(request.getIsActive());

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
            category.setParent(parent.getParent());
        }

        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Transactional
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        categoryRepository.deleteById(id);
    }
}
