package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.CouponCreationRequest;
import com.example.cellphones_spring.dto.request.CouponUpdateRequest;
import com.example.cellphones_spring.dto.response.CouponResponse;
import com.example.cellphones_spring.entity.Coupon;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.CouponMapper;
import com.example.cellphones_spring.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public List<CouponResponse> getAll() {
        return couponRepository.findAll().stream()
                .map(couponMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CouponResponse getById(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COUPON_NOT_EXISTED));
        return couponMapper.toResponse(coupon);
    }

    @Transactional
    public CouponResponse create(CouponCreationRequest request) {
        Coupon coupon = Coupon.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .value(request.getValue())
                .minOrderAmount(request.getMinOrderAmount() != null ? request.getMinOrderAmount() : BigDecimal.ZERO)
                .maxDiscountAmount(request.getMaxDiscountAmount() != null ? request.getMaxDiscountAmount() : BigDecimal.ZERO)
                .usageLimit(request.getUsageLimit())
                .usedCount(0)
                .userUsageLimit(request.getUserUsageLimit() != null ? request.getUserUsageLimit() : 1)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        coupon = couponRepository.save(coupon);
        return couponMapper.toResponse(coupon);
    }

    @Transactional
    public CouponResponse update(Long id, CouponUpdateRequest request) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COUPON_NOT_EXISTED));

        if (request.getCode() != null) coupon.setCode(request.getCode());
        if (request.getName() != null) coupon.setName(request.getName());
        if (request.getDescription() != null) coupon.setDescription(request.getDescription());
        if (request.getType() != null) coupon.setType(request.getType());
        if (request.getValue() != null) coupon.setValue(request.getValue());
        if (request.getMinOrderAmount() != null) coupon.setMinOrderAmount(request.getMinOrderAmount());
        if (request.getMaxDiscountAmount() != null) coupon.setMaxDiscountAmount(request.getMaxDiscountAmount());
        if (request.getUsageLimit() != null) coupon.setUsageLimit(request.getUsageLimit());
        if (request.getUserUsageLimit() != null) coupon.setUserUsageLimit(request.getUserUsageLimit());
        if (request.getStartDate() != null) coupon.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) coupon.setEndDate(request.getEndDate());
        if (request.getIsActive() != null) coupon.setIsActive(request.getIsActive());

        coupon = couponRepository.save(coupon);
        return couponMapper.toResponse(coupon);
    }

    @Transactional
    public void delete(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new AppException(ErrorCode.COUPON_NOT_EXISTED);
        }
        couponRepository.deleteById(id);
    }
}
