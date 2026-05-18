package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.UserAddressCreationRequest;
import com.example.cellphones_spring.dto.request.UserAddressUpdateRequest;
import com.example.cellphones_spring.dto.response.UserAddressResponse;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.entity.UserAddress;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.UserAddressMapper;
import com.example.cellphones_spring.repository.UserAddressRepository;
import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;
    private final UserAddressMapper userAddressMapper;

    public List<UserAddressResponse> getAll() {
        return userAddressRepository.findAll().stream()
                .map(userAddressMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserAddressResponse getById(Long id) {
        UserAddress address = userAddressRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ADDRESS_NOT_EXISTED));
        return userAddressMapper.toResponse(address);
    }

    @Transactional
    public UserAddressResponse create(UserAddressCreationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        UserAddress address = UserAddress.builder()
                .user(user)
                .recipientName(request.getRecipientName())
                .recipientPhone(request.getRecipientPhone())
                .province(request.getProvince())
                .district(request.getDistrict())
                .ward(request.getWard())
                .addressDetail(request.getAddressDetail())
                .isDefault(request.getIsDefault() != null ? request.getIsDefault() : false)
                .build();

        address = userAddressRepository.save(address);
        return userAddressMapper.toResponse(address);
    }

    @Transactional
    public UserAddressResponse update(Long id, UserAddressUpdateRequest request) {
        UserAddress address = userAddressRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ADDRESS_NOT_EXISTED));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            address.setUser(user);
        }

        if (request.getRecipientName() != null) address.setRecipientName(request.getRecipientName());
        if (request.getRecipientPhone() != null) address.setRecipientPhone(request.getRecipientPhone());
        if (request.getProvince() != null) address.setProvince(request.getProvince());
        if (request.getDistrict() != null) address.setDistrict(request.getDistrict());
        if (request.getWard() != null) address.setWard(request.getWard());
        if (request.getAddressDetail() != null) address.setAddressDetail(request.getAddressDetail());
        if (request.getIsDefault() != null) address.setIsDefault(request.getIsDefault());

        address = userAddressRepository.save(address);
        return userAddressMapper.toResponse(address);
    }

    @Transactional
    public void delete(Long id) {
        if (!userAddressRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_ADDRESS_NOT_EXISTED);
        }
        userAddressRepository.deleteById(id);
    }
}
