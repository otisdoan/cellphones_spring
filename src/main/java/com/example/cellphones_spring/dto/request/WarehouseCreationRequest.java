package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseCreationRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Code is required")
    private String code;
    
    private String address;
    private String phone;
    private String managerName;
    private Boolean isActive;
}
