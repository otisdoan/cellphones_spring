package com.example.cellphones_spring.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class CouponUpdateRequest {
    private String code;
    private String name;
    private String description;
    private String type;
    private BigDecimal value;
    private BigDecimal minOrderAmount;
    private BigDecimal maxDiscountAmount;
    private Integer usageLimit;
    private Integer userUsageLimit;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Boolean isActive;
}
