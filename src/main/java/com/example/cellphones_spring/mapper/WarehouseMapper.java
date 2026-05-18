package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.WarehouseResponse;
import com.example.cellphones_spring.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public WarehouseResponse toResponse(Warehouse warehouse) {
        if (warehouse == null) {
            return null;
        }

        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .code(warehouse.getCode())
                .address(warehouse.getAddress())
                .phone(warehouse.getPhone())
                .managerName(warehouse.getManagerName())
                .isActive(warehouse.getIsActive())
                .build();
    }
}
