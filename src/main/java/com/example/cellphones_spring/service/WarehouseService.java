package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.WarehouseCreationRequest;
import com.example.cellphones_spring.dto.request.WarehouseUpdateRequest;
import com.example.cellphones_spring.dto.response.WarehouseResponse;
import com.example.cellphones_spring.entity.Warehouse;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.WarehouseMapper;
import com.example.cellphones_spring.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public List<WarehouseResponse> getAll() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public WarehouseResponse getById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_EXISTED));
        return warehouseMapper.toResponse(warehouse);
    }

    @Transactional
    public WarehouseResponse create(WarehouseCreationRequest request) {
        Warehouse warehouse = Warehouse.builder()
                .name(request.getName())
                .code(request.getCode())
                .address(request.getAddress())
                .phone(request.getPhone())
                .managerName(request.getManagerName())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();

        warehouse = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponse(warehouse);
    }

    @Transactional
    public WarehouseResponse update(Long id, WarehouseUpdateRequest request) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_EXISTED));

        if (request.getName() != null) warehouse.setName(request.getName());
        if (request.getCode() != null) warehouse.setCode(request.getCode());
        if (request.getAddress() != null) warehouse.setAddress(request.getAddress());
        if (request.getPhone() != null) warehouse.setPhone(request.getPhone());
        if (request.getManagerName() != null) warehouse.setManagerName(request.getManagerName());
        if (request.getIsActive() != null) warehouse.setIsActive(request.getIsActive());

        warehouse = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponse(warehouse);
    }

    @Transactional
    public void delete(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new AppException(ErrorCode.WAREHOUSE_NOT_EXISTED);
        }
        warehouseRepository.deleteById(id);
    }
}
