package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.ChatSessionResponse;
import com.example.cellphones_spring.entity.ChatSession;
import org.springframework.stereotype.Component;

@Component
public class ChatSessionMapper {

    public ChatSessionResponse toResponse(ChatSession session) {
        if (session == null) {
            return null;
        }

        return ChatSessionResponse.builder()
                .id(session.getId())
                .sessionId(session.getSessionId())
                .userId(session.getUser() != null ? session.getUser().getId() : null)
                .startedAt(session.getStartedAt())
                .lastActivityAt(session.getLastActivityAt())
                .endedAt(session.getEndedAt())
                .metadata(session.getMetadata())
                .build();
    }
}
