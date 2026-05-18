package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class UserAddressUpdateRequest {
    private Long userId;
    private String recipientName;
    private String recipientPhone;
    private String province;
    private String district;
    private String ward;
    private String addressDetail;
    private Boolean isDefault;
}
