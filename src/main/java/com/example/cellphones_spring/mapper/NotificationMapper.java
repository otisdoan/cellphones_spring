package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.NotificationResponse;
import com.example.cellphones_spring.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUser() != null ? notification.getUser().getId() : null)
                .type(notification.getType())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .orderId(notification.getOrder() != null ? notification.getOrder().getId() : null)
                .orderNumber(notification.getOrderNumber())
                .isRead(notification.getIsRead())
                .iconType(notification.getIconType())
                .metadata(notification.getMetadata())
                .build();
    }
}
