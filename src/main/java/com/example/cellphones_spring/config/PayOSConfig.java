package com.example.cellphones_spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.payos.PayOS;

@Configuration
public class PayOSConfig {

    @Value("${payos.api-key}")
    private String apiKey;

    @Value("${payos.client-id}")
    private String clientId;

    @Value("${payos.check-sum}")
    private String checkSum;

    @Bean
    public PayOS payOS() {
        return new PayOS(clientId, apiKey, checkSum);
    }
}
