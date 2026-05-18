package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.ChatSessionCreationRequest;
import com.example.cellphones_spring.dto.request.ChatSessionUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.ChatSessionResponse;
import com.example.cellphones_spring.service.ChatSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.CHAT_SESSIONS)
@RequiredArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ChatSessionResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(chatSessionService.getAll(), "Get all chat sessions successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatSessionResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(chatSessionService.getById(id), "Get chat session by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ChatSessionResponse>> create(@Valid @RequestBody ChatSessionCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(chatSessionService.create(request), "Create chat session successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatSessionResponse>> update(@PathVariable Long id, @Valid @RequestBody ChatSessionUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(chatSessionService.update(id, request), "Update chat session successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        chatSessionService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete chat session successfully"));
    }
}
