package com.example.cellphones_spring.dto.request;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class InventoryUpdateRequest {
    private Long warehouseId;
    private Long productId;
    private Long variantId;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer minStockAlert;
    private OffsetDateTime lastUpdated;
}
