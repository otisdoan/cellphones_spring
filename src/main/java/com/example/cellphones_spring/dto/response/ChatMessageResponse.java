package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Builder
public class ChatMessageResponse {
    private Long id;
    private String sessionId;
    private String role;
    private String content;
    private String productsShown;
    private String metadata;
    private OffsetDateTime createdAt;
}
