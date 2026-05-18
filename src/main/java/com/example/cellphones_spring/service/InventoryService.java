package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.InventoryCreationRequest;
import com.example.cellphones_spring.dto.request.InventoryUpdateRequest;
import com.example.cellphones_spring.dto.response.InventoryResponse;
import com.example.cellphones_spring.entity.Inventory;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductVariant;
import com.example.cellphones_spring.entity.Warehouse;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.InventoryMapper;
import com.example.cellphones_spring.repository.InventoryRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import com.example.cellphones_spring.repository.ProductVariantRepository;
import com.example.cellphones_spring.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final InventoryMapper inventoryMapper;

    public List<InventoryResponse> getAll() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public InventoryResponse getById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVENTORY_NOT_EXISTED));
        return inventoryMapper.toResponse(inventory);
    }

    @Transactional
    public InventoryResponse create(InventoryCreationRequest request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_EXISTED));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ProductVariant variant = null;
        if (request.getVariantId() != null) {
            variant = productVariantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
        }

        Inventory inventory = Inventory.builder()
                .warehouse(warehouse)
                .product(product)
                .variant(variant)
                .quantity(request.getQuantity() != null ? request.getQuantity() : 0)
                .reservedQuantity(request.getReservedQuantity() != null ? request.getReservedQuantity() : 0)
                .minStockAlert(request.getMinStockAlert() != null ? request.getMinStockAlert() : 10)
                .lastUpdated(request.getLastUpdated())
                .build();

        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(inventory);
    }

    @Transactional
    public InventoryResponse update(Long id, InventoryUpdateRequest request) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVENTORY_NOT_EXISTED));

        if (request.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                    .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_EXISTED));
            inventory.setWarehouse(warehouse);
        }

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            inventory.setProduct(product);
        }

        if (request.getVariantId() != null) {
            ProductVariant variant = productVariantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
            inventory.setVariant(variant);
        }

        if (request.getQuantity() != null) inventory.setQuantity(request.getQuantity());
        if (request.getReservedQuantity() != null) inventory.setReservedQuantity(request.getReservedQuantity());
        if (request.getMinStockAlert() != null) inventory.setMinStockAlert(request.getMinStockAlert());
        if (request.getLastUpdated() != null) inventory.setLastUpdated(request.getLastUpdated());

        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(inventory);
    }

    @Transactional
    public void delete(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.INVENTORY_NOT_EXISTED);
        }
        inventoryRepository.deleteById(id);
    }
}
