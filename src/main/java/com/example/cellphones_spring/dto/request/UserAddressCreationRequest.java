package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAddressCreationRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotBlank(message = "Recipient name is required")
    private String recipientName;
    
    @NotBlank(message = "Recipient phone is required")
    private String recipientPhone;
    
    @NotBlank(message = "Province is required")
    private String province;
    
    @NotBlank(message = "District is required")
    private String district;
    
    @NotBlank(message = "Ward is required")
    private String ward;
    
    private String addressDetail;
    private Boolean isDefault;
}
