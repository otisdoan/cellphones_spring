package com.example.cellphones_spring.controller;

import com.example.cellphones_spring.constant.ApiConstant;
import com.example.cellphones_spring.dto.request.ChatMessageCreationRequest;
import com.example.cellphones_spring.dto.request.ChatMessageUpdateRequest;
import com.example.cellphones_spring.dto.response.ApiResponse;
import com.example.cellphones_spring.dto.response.ChatMessageResponse;
import com.example.cellphones_spring.service.ChatMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}" + ApiConstant.CHAT_MESSAGES)
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ChatMessageResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(chatMessageService.getAll(), "Get all chat messages successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatMessageResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(chatMessageService.getById(id), "Get chat message by id successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ChatMessageResponse>> create(@Valid @RequestBody ChatMessageCreationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(chatMessageService.create(request), "Create chat message successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatMessageResponse>> update(@PathVariable Long id, @Valid @RequestBody ChatMessageUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(chatMessageService.update(id, request), "Update chat message successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        chatMessageService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Delete chat message successfully"));
    }
}
