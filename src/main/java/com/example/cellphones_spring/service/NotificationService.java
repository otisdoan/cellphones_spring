package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.NotificationCreationRequest;
import com.example.cellphones_spring.dto.request.NotificationUpdateRequest;
import com.example.cellphones_spring.dto.response.NotificationResponse;
import com.example.cellphones_spring.entity.Notification;
import com.example.cellphones_spring.entity.Order;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.enums.NotificationType;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.NotificationMapper;
import com.example.cellphones_spring.repository.NotificationRepository;
import com.example.cellphones_spring.repository.OrderRepository;
import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationResponse> getAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponse getById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_EXISTED));
        return notificationMapper.toResponse(notification);
    }

    @Transactional
    public NotificationResponse create(NotificationCreationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Order order = null;
        if (request.getOrderId() != null) {
            order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        }

        Notification notification = Notification.builder()
                .user(user)
                .type(request.getType() != null ? request.getType() : NotificationType.ORDER)
                .title(request.getTitle())
                .message(request.getMessage())
                .order(order)
                .orderNumber(request.getOrderNumber())
                .isRead(request.getIsRead() != null ? request.getIsRead() : false)
                .iconType(request.getIconType())
                .metadata(request.getMetadata())
                .build();

        notification = notificationRepository.save(notification);
        return notificationMapper.toResponse(notification);
    }

    @Transactional
    public NotificationResponse update(Long id, NotificationUpdateRequest request) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_EXISTED));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            notification.setUser(user);
        }

        if (request.getOrderId() != null) {
            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
            notification.setOrder(order);
        }

        if (request.getType() != null) notification.setType(request.getType());
        if (request.getTitle() != null) notification.setTitle(request.getTitle());
        if (request.getMessage() != null) notification.setMessage(request.getMessage());
        if (request.getOrderNumber() != null) notification.setOrderNumber(request.getOrderNumber());
        if (request.getIsRead() != null) notification.setIsRead(request.getIsRead());
        if (request.getIconType() != null) notification.setIconType(request.getIconType());
        if (request.getMetadata() != null) notification.setMetadata(request.getMetadata());

        notification = notificationRepository.save(notification);
        return notificationMapper.toResponse(notification);
    }

    @Transactional
    public void delete(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new AppException(ErrorCode.NOTIFICATION_NOT_EXISTED);
        }
        notificationRepository.deleteById(id);
    }
}
