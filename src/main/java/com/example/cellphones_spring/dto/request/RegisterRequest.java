package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required!")
    private String fullName;

    @NotBlank(message = "Phone is required!")
    private String phone;

    @NotNull(message = "Date of birth is required!")
    private LocalDate dateOfBirth;

    private String email;

    @NotBlank(message = "Password is required!")
    private String passwordRegister;

}
