package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaymentRequest {

    @NotNull(message = "Order code is required")
    private Long orderCode;

    @NotNull(message = "Amount is required")
    private Long amount;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Cancel URL is required")
    private String cancelUrl;

    @NotBlank(message = "Return URL is required")
    private String returnUrl;

}
