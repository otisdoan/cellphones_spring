package com.example.cellphones_spring.dto.request;

import com.example.cellphones_spring.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationUpdateRequest {
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
