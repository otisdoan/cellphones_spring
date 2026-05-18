package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class CouponCreationRequest {
    @NotBlank(message = "Code is required")
    private String code;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotBlank(message = "Type is required")
    private String type;
    
    @NotNull(message = "Value is required")
    private BigDecimal value;
    
    private BigDecimal minOrderAmount;
    private BigDecimal maxDiscountAmount;
    private Integer usageLimit;
    private Integer userUsageLimit;
    
    @NotNull(message = "Start date is required")
    private OffsetDateTime startDate;
    
    @NotNull(message = "End date is required")
    private OffsetDateTime endDate;
    
    private Boolean isActive;
}
