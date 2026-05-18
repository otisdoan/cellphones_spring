package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class WarehouseUpdateRequest {
    private String name;
    private String code;
    private String address;
    private String phone;
    private String managerName;
    private Boolean isActive;
}
