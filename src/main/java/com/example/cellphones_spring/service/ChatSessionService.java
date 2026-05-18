package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.ChatSessionCreationRequest;
import com.example.cellphones_spring.dto.request.ChatSessionUpdateRequest;
import com.example.cellphones_spring.dto.response.ChatSessionResponse;
import com.example.cellphones_spring.entity.ChatSession;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.ChatSessionMapper;
import com.example.cellphones_spring.repository.ChatSessionRepository;
import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;
    private final UserRepository userRepository;
    private final ChatSessionMapper chatSessionMapper;

    public List<ChatSessionResponse> getAll() {
        return chatSessionRepository.findAll().stream()
                .map(chatSessionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ChatSessionResponse getById(Long id) {
        ChatSession session = chatSessionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CHAT_SESSION_NOT_EXISTED));
        return chatSessionMapper.toResponse(session);
    }

    @Transactional
    public ChatSessionResponse create(ChatSessionCreationRequest request) {
        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        }

        ChatSession session = ChatSession.builder()
                .sessionId(request.getSessionId())
                .user(user)
                .startedAt(request.getStartedAt())
                .lastActivityAt(request.getLastActivityAt())
                .endedAt(request.getEndedAt())
                .metadata(request.getMetadata())
                .build();

        session = chatSessionRepository.save(session);
        return chatSessionMapper.toResponse(session);
    }

    @Transactional
    public ChatSessionResponse update(Long id, ChatSessionUpdateRequest request) {
        ChatSession session = chatSessionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CHAT_SESSION_NOT_EXISTED));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            session.setUser(user);
        }

        if (request.getSessionId() != null) session.setSessionId(request.getSessionId());
        if (request.getStartedAt() != null) session.setStartedAt(request.getStartedAt());
        if (request.getLastActivityAt() != null) session.setLastActivityAt(request.getLastActivityAt());
        if (request.getEndedAt() != null) session.setEndedAt(request.getEndedAt());
        if (request.getMetadata() != null) session.setMetadata(request.getMetadata());

        session = chatSessionRepository.save(session);
        return chatSessionMapper.toResponse(session);
    }

    @Transactional
    public void delete(Long id) {
        if (!chatSessionRepository.existsById(id)) {
            throw new AppException(ErrorCode.CHAT_SESSION_NOT_EXISTED);
        }
        chatSessionRepository.deleteById(id);
    }
}
