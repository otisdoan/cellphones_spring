package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.InventoryResponse;
import com.example.cellphones_spring.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponse toResponse(Inventory inventory) {
        if (inventory == null) {
            return null;
        }

        return InventoryResponse.builder()
                .id(inventory.getId())
                .warehouseId(inventory.getWarehouse() != null ? inventory.getWarehouse().getId() : null)
                .productId(inventory.getProduct() != null ? inventory.getProduct().getId() : null)
                .variantId(inventory.getVariant() != null ? inventory.getVariant().getId() : null)
                .quantity(inventory.getQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .minStockAlert(inventory.getMinStockAlert())
                .lastUpdated(inventory.getLastUpdated())
                .build();
    }
}
