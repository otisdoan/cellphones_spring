package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.OrderCreationRequest;
import com.example.cellphones_spring.dto.request.OrderUpdateRequest;
import com.example.cellphones_spring.dto.response.OrderResponse;
import com.example.cellphones_spring.entity.Order;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.enums.OrderStatus;
import com.example.cellphones_spring.enums.PaymentStatus;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.OrderMapper;
import com.example.cellphones_spring.repository.OrderRepository;
import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse create(OrderCreationRequest request) {
        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        }

        Order order = Order.builder()
                .orderNumber(request.getOrderNumber())
                .user(user)
                .guestEmail(request.getGuestEmail())
                .guestPhone(request.getGuestPhone())
                .status(request.getStatus() != null ? request.getStatus() : OrderStatus.PENDING)
                .paymentStatus(request.getPaymentStatus() != null ? request.getPaymentStatus() : PaymentStatus.PENDING)
                .paymentMethod(request.getPaymentMethod())
                .subtotal(request.getSubtotal())
                .discountAmount(request.getDiscountAmount() != null ? request.getDiscountAmount() : BigDecimal.ZERO)
                .shippingFee(request.getShippingFee() != null ? request.getShippingFee() : BigDecimal.ZERO)
                .taxAmount(request.getTaxAmount() != null ? request.getTaxAmount() : BigDecimal.ZERO)
                .totalAmount(request.getTotalAmount())
                .currency(request.getCurrency() != null ? request.getCurrency() : "VND")
                .notes(request.getNotes())
                .build();

        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse update(Long id, OrderUpdateRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            order.setUser(user);
        }

        if (request.getGuestEmail() != null) order.setGuestEmail(request.getGuestEmail());
        if (request.getGuestPhone() != null) order.setGuestPhone(request.getGuestPhone());
        if (request.getStatus() != null) order.setStatus(request.getStatus());
        if (request.getPaymentStatus() != null) order.setPaymentStatus(request.getPaymentStatus());
        if (request.getPaymentMethod() != null) order.setPaymentMethod(request.getPaymentMethod());
        if (request.getSubtotal() != null) order.setSubtotal(request.getSubtotal());
        if (request.getDiscountAmount() != null) order.setDiscountAmount(request.getDiscountAmount());
        if (request.getShippingFee() != null) order.setShippingFee(request.getShippingFee());
        if (request.getTaxAmount() != null) order.setTaxAmount(request.getTaxAmount());
        if (request.getTotalAmount() != null) order.setTotalAmount(request.getTotalAmount());
        if (request.getCurrency() != null) order.setCurrency(request.getCurrency());
        if (request.getNotes() != null) order.setNotes(request.getNotes());

        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Transactional
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new AppException(ErrorCode.ORDER_NOT_EXISTED);
        }
        orderRepository.deleteById(id);
    }
}
