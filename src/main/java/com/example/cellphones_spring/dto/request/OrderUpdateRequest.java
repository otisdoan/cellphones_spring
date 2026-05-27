package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.OrderStatus;
import com.example.cellphones_spring.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderUpdateRequest {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("guest_email")
    private String guestEmail;

    @JsonProperty("guest_phone")
    private String guestPhone;

    private OrderStatus status;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    @JsonProperty("payment_method")
    private String paymentMethod;

    private BigDecimal subtotal;

    @JsonProperty("discount_amount")
    private BigDecimal discountAmount;

    @JsonProperty("shipping_fee")
    private BigDecimal shippingFee;

    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    private String currency;
    private String notes;
}
