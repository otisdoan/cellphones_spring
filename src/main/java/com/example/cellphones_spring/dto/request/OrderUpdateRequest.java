package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.OrderStatus;
import com.example.cellphones_spring.enums.PaymentStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderUpdateRequest {
    private Long userId;
    private String guestEmail;
    private String guestPhone;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal shippingFee;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private String currency;
    private String notes;
}
