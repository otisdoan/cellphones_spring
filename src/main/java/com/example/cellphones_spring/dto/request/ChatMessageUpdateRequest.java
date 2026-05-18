package com.example.cellphones_spring.dto.request;

import lombok.Data;

@Data
public class ChatMessageUpdateRequest {
    private String sessionId;
    private String role;
    private String content;
    private String productsShown;
    private String metadata;
}
