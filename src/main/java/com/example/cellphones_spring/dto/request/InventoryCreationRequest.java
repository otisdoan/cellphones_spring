package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class InventoryCreationRequest {
    @NotNull(message = "Warehouse ID is required")
    private Long warehouseId;
    
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    private Long variantId;
    
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    
    private Integer reservedQuantity;
    private Integer minStockAlert;
    private OffsetDateTime lastUpdated;
}
