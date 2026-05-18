package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatMessageCreationRequest {
    @NotBlank(message = "Session ID is required")
    private String sessionId;
    
    @NotBlank(message = "Role is required")
    private String role;
    
    @NotBlank(message = "Content is required")
    private String content;
    
    private String productsShown;
    private String metadata;
}
