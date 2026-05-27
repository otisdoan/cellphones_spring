package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PayOS payOS;

    public CreatePaymentLinkResponse createPayment(CreatePaymentRequest createPaymentRequest) {
        CreatePaymentLinkRequest paymentRequest = CreatePaymentLinkRequest.builder()
                .orderCode(createPaymentRequest.getOrderCode())
                .amount(createPaymentRequest.getAmount())
                .description(createPaymentRequest.getDescription())
                .cancelUrl(createPaymentRequest.getCancelUrl())
                .returnUrl(createPaymentRequest.getReturnUrl())
                .build();
        return payOS.paymentRequests().create(paymentRequest);
    }
}
