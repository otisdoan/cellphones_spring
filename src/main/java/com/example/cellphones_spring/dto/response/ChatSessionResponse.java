package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class ChatSessionResponse {
    private Long id;
    private String sessionId;
    private Long userId;
    private OffsetDateTime startedAt;
    private OffsetDateTime lastActivityAt;
    private OffsetDateTime endedAt;
    private String metadata;
}
