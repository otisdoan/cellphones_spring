package com.example.cellphones_spring.entity;

import com.example.cellphones_spring.common.entity.BaseEntity;
import com.example.cellphones_spring.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type = NotificationType.ORDER;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_number", length = 50)
    private String orderNumber;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "icon_type", length = 50)
    private String iconType;

    @Column(columnDefinition = "jsonb")
    private String metadata;
}
