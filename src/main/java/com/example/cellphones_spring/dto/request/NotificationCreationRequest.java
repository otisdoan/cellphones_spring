package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationCreationRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private NotificationType type;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String message;
    private Long orderId;
    private String orderNumber;
    private Boolean isRead;
    private String iconType;
    private String metadata;
}
