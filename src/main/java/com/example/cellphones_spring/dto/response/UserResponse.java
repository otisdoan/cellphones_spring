package com.example.cellphones_spring.dto.response;

import com.example.cellphones_spring.enums.Gender;
import com.example.cellphones_spring.enums.Role;
import com.example.cellphones_spring.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String phone;
    private String fullName;
    private Role role;
    private Status status;
    private Gender gender;
    private String avatarUrl;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private LocalDate dateOfBirth;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
