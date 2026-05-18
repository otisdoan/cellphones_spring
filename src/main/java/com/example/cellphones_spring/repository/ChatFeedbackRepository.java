package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.ChatFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatFeedbackRepository extends JpaRepository<ChatFeedback, Long> {
}
