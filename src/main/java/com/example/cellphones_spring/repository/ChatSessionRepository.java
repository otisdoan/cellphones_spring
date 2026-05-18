package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
}
