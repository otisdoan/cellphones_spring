package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.ChatAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatAnalyticsRepository extends JpaRepository<ChatAnalytics, Long> {
}
