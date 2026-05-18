package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.dto.request.LoginGoogleRequest;
import com.example.cellphones_spring.dto.request.LoginRequest;
import com.example.cellphones_spring.dto.request.RegisterRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.LoginResponse;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.cellphones_spring.constant.ApiConstant;

@Tag(name = "Authentication API")
@RestController
@RequestMapping("${api.prefix}" + ApiConstant.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiConstant.REGISTER)
    public ResponseEntity<ApiResponse<User>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        User user = authService.register(registerRequest);
        return ResponseEntity.ok(ApiResponse.success(user, "Register successfully"));
    }

    @PostMapping(ApiConstant.LOGIN)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(loginRequest, response);
        return ResponseEntity.ok(ApiResponse.success(loginResponse, "Login successfully"));
    }

    @PostMapping(ApiConstant.GOOGLE)
    public ResponseEntity<ApiResponse<LoginResponse>> loginGoogle(@RequestBody @Valid LoginGoogleRequest loginGoogleRequest, HttpServletResponse response) {
        LoginResponse loginResponse = authService.loginGoogle(loginGoogleRequest, response);
        return ResponseEntity.ok(ApiResponse.success(loginResponse, "Login with Google successfully"));
    }

    @PostMapping(ApiConstant.LOGOUT)
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletResponse response){
        authService.logout(response);
        return ResponseEntity.ok(ApiResponse.success(null, "Logout successfully"));
    }

    @PostMapping(ApiConstant.REFRESH_TOKEN)
    public ResponseEntity<ApiResponse<Void>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        authService.refreshToken(request, response);
        return ResponseEntity.ok(ApiResponse.success(null, "Refresh token successfully"));
    }
}
