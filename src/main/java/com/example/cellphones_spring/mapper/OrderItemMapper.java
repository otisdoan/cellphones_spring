package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.OrderItemResponse;
import com.example.cellphones_spring.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null)
                .productId(orderItem.getProduct() != null ? orderItem.getProduct().getId() : null)
                .variantId(orderItem.getVariant() != null ? orderItem.getVariant().getId() : null)
                .productName(orderItem.getProductName())
                .variantName(orderItem.getVariantName())
                .sku(orderItem.getSku())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .total(orderItem.getTotal())
                .salePrice(orderItem.getSalePrice())
                .imageUrl(orderItem.getImageUrl())
                .stockQuantity(orderItem.getStockQuantity())
                .build();
    }
}
