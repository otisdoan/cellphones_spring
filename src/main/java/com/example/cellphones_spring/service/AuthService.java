package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.LoginGoogleRequest;
import com.example.cellphones_spring.dto.request.LoginRequest;
import com.example.cellphones_spring.dto.request.RegisterRequest;
import com.example.cellphones_spring.dto.response.LoginResponse;
import com.example.cellphones_spring.dto.response.GoogleUserInfo;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.repository.UserRepository;
import com.example.cellphones_spring.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final GoogleService googleService;

    public User register(RegisterRequest registerRequest){
        if (userRepository.existsByPhone(registerRequest.getPhone())) {
            throw new AppException(ErrorCode.PHONE_EXISTED);
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        var user = User.builder()
                .fullName(registerRequest.getFullName())
                .dateOfBirth(registerRequest.getDateOfBirth())
                .phone(registerRequest.getPhone())
                .email(registerRequest.getEmail())
                .passwordHash(passwordEncoder.encode(registerRequest.getPasswordRegister()))
                .build();
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhone(),
                        loginRequest.getPasswordLogin()
                )
        );
        var user = userRepository.findByPhone(loginRequest.getPhone())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(15 * 60)
                .build();


        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponse loginGoogle(LoginGoogleRequest loginGoogleRequest, HttpServletResponse response) {
        GoogleUserInfo payload = googleService.getUserInfo(loginGoogleRequest.getAccessToken());
        log.info("Google user info: {}", payload);
        String email = payload.getEmail();
        String name = payload.getName();
        String avatarUrl = payload.getPicture();

        User user = userRepository
                .findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .fullName(name)
                            .avatarUrl(avatarUrl)
                            .build();
                    return userRepository.save(newUser);
                });

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(15 * 60)
                .build();


        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void logout(HttpServletResponse response) {
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = null;
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("refreshToken".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }

            if (token == null) {
                throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);
            }

            String username = jwtService.extractUsername(token);

            User user;
            if (username.contains("@")) {
                user = userRepository.findByEmail(username)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            } else {
                user = userRepository.findByPhone(username)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            }

            if (!jwtService.isTokenValid(token, user)) {
                throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);
            }

            var accessToken = jwtService.generateAccessToken(user);

            ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(15 * 60)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());

        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
