package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String role;
    private String status;
}
