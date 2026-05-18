package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class InventoryResponse {
    private Long id;
    private Long warehouseId;
    private Long productId;
    private Long variantId;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer minStockAlert;
    private OffsetDateTime lastUpdated;
}
