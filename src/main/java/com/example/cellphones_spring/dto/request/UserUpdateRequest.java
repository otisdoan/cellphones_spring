package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.Gender;
import com.example.cellphones_spring.enums.Role;
import com.example.cellphones_spring.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String phone;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String avatarUrl;
    private Role role;
    private Status status;
}
