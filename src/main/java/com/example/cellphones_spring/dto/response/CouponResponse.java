package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class CouponResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String type;
    private BigDecimal value;
    private BigDecimal minOrderAmount;
    private BigDecimal maxDiscountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private Integer userUsageLimit;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Boolean isActive;
}
