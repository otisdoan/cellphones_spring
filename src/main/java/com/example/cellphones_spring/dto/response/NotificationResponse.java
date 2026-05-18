package com.example.cellphones_spring.dto.response;

import com.example.cellphones_spring.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {
    private Long id;
    private Long userId;
    private NotificationType type;
    private String title;
    private String message;
    private Long orderId;
    private String orderNumber;
    private Boolean isRead;
    private String iconType;
    private String metadata;
}
