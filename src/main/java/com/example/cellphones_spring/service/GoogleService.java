package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.response.GoogleUserInfo;
import com.example.cellphones_spring.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GoogleService {

    private final RestTemplate restTemplate = new RestTemplate();

    public GoogleUserInfo getUserInfo(String accessToken) {

        String url = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<GoogleUserInfo> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    GoogleUserInfo.class
            );
            return response.getBody();
        } catch (Exception e) {
            log.error("Error verifying Google access token: ", e);
            throw new RuntimeException(ErrorCode.INVALID_GOOGLE_TOKEN.getMessage());
        }
    }
}
