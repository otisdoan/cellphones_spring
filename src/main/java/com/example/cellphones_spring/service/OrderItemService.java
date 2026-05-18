package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.OrderItemCreationRequest;
import com.example.cellphones_spring.dto.request.OrderItemUpdateRequest;
import com.example.cellphones_spring.dto.response.OrderItemResponse;
import com.example.cellphones_spring.entity.Order;
import com.example.cellphones_spring.entity.OrderItem;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductVariant;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.OrderItemMapper;
import com.example.cellphones_spring.repository.OrderItemRepository;
import com.example.cellphones_spring.repository.OrderRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import com.example.cellphones_spring.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemResponse> getAll() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderItemResponse getById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_EXISTED));
        return orderItemMapper.toResponse(orderItem);
    }

    @Transactional
    public OrderItemResponse create(OrderItemCreationRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ProductVariant variant = null;
        if (request.getVariantId() != null) {
            variant = productVariantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
        }

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .variant(variant)
                .productName(request.getProductName())
                .variantName(request.getVariantName())
                .sku(request.getSku())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .total(request.getTotal())
                .salePrice(request.getSalePrice())
                .imageUrl(request.getImageUrl())
                .stockQuantity(request.getStockQuantity())
                .build();

        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toResponse(orderItem);
    }

    @Transactional
    public OrderItemResponse update(Long id, OrderItemUpdateRequest request) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_EXISTED));

        if (request.getOrderId() != null) {
            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
            orderItem.setOrder(order);
        }

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            orderItem.setProduct(product);
        }

        if (request.getVariantId() != null) {
            ProductVariant variant = productVariantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
            orderItem.setVariant(variant);
        }

        if (request.getProductName() != null) orderItem.setProductName(request.getProductName());
        if (request.getVariantName() != null) orderItem.setVariantName(request.getVariantName());
        if (request.getSku() != null) orderItem.setSku(request.getSku());
        if (request.getPrice() != null) orderItem.setPrice(request.getPrice());
        if (request.getQuantity() != null) orderItem.setQuantity(request.getQuantity());
        if (request.getTotal() != null) orderItem.setTotal(request.getTotal());
        if (request.getSalePrice() != null) orderItem.setSalePrice(request.getSalePrice());
        if (request.getImageUrl() != null) orderItem.setImageUrl(request.getImageUrl());
        if (request.getStockQuantity() != null) orderItem.setStockQuantity(request.getStockQuantity());

        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toResponse(orderItem);
    }

    @Transactional
    public void delete(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new AppException(ErrorCode.ORDER_ITEM_NOT_EXISTED);
        }
        orderItemRepository.deleteById(id);
    }
}
