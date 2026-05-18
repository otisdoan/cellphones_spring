package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ChatMessageCreationRequest;
import com.example.cellphones_spring.dto.request.ChatMessageUpdateRequest;
import com.example.cellphones_spring.dto.response.ChatMessageResponse;
import com.example.cellphones_spring.entity.ChatMessage;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ChatMessageMapper;
import com.example.cellphones_spring.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    public List<ChatMessageResponse> getAll() {
        return chatMessageRepository.findAll().stream()
                .map(chatMessageMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ChatMessageResponse getById(Long id) {
        ChatMessage message = chatMessageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CHAT_MESSAGE_NOT_EXISTED));
        return chatMessageMapper.toResponse(message);
    }

    @Transactional
    public ChatMessageResponse create(ChatMessageCreationRequest request) {
        ChatMessage message = ChatMessage.builder()
                .sessionId(request.getSessionId())
                .role(request.getRole())
                .content(request.getContent())
                .productsShown(request.getProductsShown())
                .metadata(request.getMetadata())
                .build();

        message = chatMessageRepository.save(message);
        return chatMessageMapper.toResponse(message);
    }

    @Transactional
    public ChatMessageResponse update(Long id, ChatMessageUpdateRequest request) {
        ChatMessage message = chatMessageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CHAT_MESSAGE_NOT_EXISTED));

        if (request.getSessionId() != null) message.setSessionId(request.getSessionId());
        if (request.getRole() != null) message.setRole(request.getRole());
        if (request.getContent() != null) message.setContent(request.getContent());
        if (request.getProductsShown() != null) message.setProductsShown(request.getProductsShown());
        if (request.getMetadata() != null) message.setMetadata(request.getMetadata());

        message = chatMessageRepository.save(message);
        return chatMessageMapper.toResponse(message);
    }

    @Transactional
    public void delete(Long id) {
        if (!chatMessageRepository.existsById(id)) {
            throw new AppException(ErrorCode.CHAT_MESSAGE_NOT_EXISTED);
        }
        chatMessageRepository.deleteById(id);
    }
}
