package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.OrderStatus;
import com.example.cellphones_spring.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreationRequest {
    @JsonProperty("order_number")
    @NotBlank(message = "Order number is required")
    private String orderNumber;

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

    @NotNull(message = "Subtotal is required")
    private BigDecimal subtotal;

    @JsonProperty("discount_amount")
    private BigDecimal discountAmount;

    @JsonProperty("shipping_fee")
    private BigDecimal shippingFee;

    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    @JsonProperty("total_amount")
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;

    private String currency;
    private String notes;

    private List<OrderItemRequest> items;


}
