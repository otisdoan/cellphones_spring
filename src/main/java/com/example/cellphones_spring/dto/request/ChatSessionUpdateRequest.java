package com.example.cellphones_spring.dto.request;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class ChatSessionUpdateRequest {
    private String sessionId;
    private Long userId;
    private OffsetDateTime startedAt;
    private OffsetDateTime lastActivityAt;
    private OffsetDateTime endedAt;
    private String metadata;
}
