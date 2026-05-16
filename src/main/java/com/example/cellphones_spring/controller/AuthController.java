package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.dto.request.LoginRequest;
import com.example.cellphones_spring.dto.request.RegisterRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.LoginResponse;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        User user = authService.register(registerRequest);
        return ResponseEntity.ok(ApiResponse.success(user, "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(loginRequest, response);
        return ResponseEntity.ok(ApiResponse.success(loginResponse, "Login successfully"));
    }

}
