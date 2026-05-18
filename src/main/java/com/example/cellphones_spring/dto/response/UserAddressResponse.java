package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressResponse {
    private Long id;
    private Long userId;
    private String recipientName;
    private String recipientPhone;
    private String province;
    private String district;
    private String ward;
    private String addressDetail;
    private Boolean isDefault;
}
