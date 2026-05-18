package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.OrderStatus;
import com.example.cellphones_spring.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderCreationRequest {
    @NotBlank(message = "Order number is required")
    private String orderNumber;
    
    private Long userId;
    private String guestEmail;
    private String guestPhone;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    
    @NotNull(message = "Subtotal is required")
    private BigDecimal subtotal;
    
    private BigDecimal discountAmount;
    private BigDecimal shippingFee;
    private BigDecimal taxAmount;
    
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;
    
    private String currency;
    private String notes;
}
