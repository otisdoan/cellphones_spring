package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.UserAddressResponse;
import com.example.cellphones_spring.entity.UserAddress;
import org.springframework.stereotype.Component;

@Component
public class UserAddressMapper {

    public UserAddressResponse toResponse(UserAddress address) {
        if (address == null) {
            return null;
        }

        return UserAddressResponse.builder()
                .id(address.getId())
                .userId(address.getUser() != null ? address.getUser().getId() : null)
                .recipientName(address.getRecipientName())
                .recipientPhone(address.getRecipientPhone())
                .province(address.getProvince())
                .district(address.getDistrict())
                .ward(address.getWard())
                .addressDetail(address.getAddressDetail())
                .isDefault(address.getIsDefault())
                .build();
    }
}
