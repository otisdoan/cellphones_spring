package com.example.cellphones_spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class ChatSessionCreationRequest {
    @NotBlank(message = "Session ID is required")
    private String sessionId;
    
    private Long userId;
    private OffsetDateTime startedAt;
    private OffsetDateTime lastActivityAt;
    private OffsetDateTime endedAt;
    private String metadata;
}
