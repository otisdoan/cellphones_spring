package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
