package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginGoogleRequest {

    @NotBlank(message = "ID token is required")
    private String accessToken;
}
