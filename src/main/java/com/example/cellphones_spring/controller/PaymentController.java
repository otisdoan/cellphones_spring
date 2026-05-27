package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.CreatePaymentRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.payos.exception.PayOSException;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkResponse;

@Slf4j
@RestController
@RequestMapping("${api.prefix}" + ApiConstant.PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/")
    ResponseEntity<ApiResponse<CreatePaymentLinkResponse>> createPayment(@Valid CreatePaymentRequest createPaymentRequest){
       try {
           return ResponseEntity.ok(ApiResponse.success(paymentService.createPayment(createPaymentRequest), "Create payment link successfully"));
       } catch (PayOSException e) {
           log.error("Error creating payment link: {}", e.getMessage());
           throw new AppException(ErrorCode.PAYMENT_FAILED);
       }
    }
}
