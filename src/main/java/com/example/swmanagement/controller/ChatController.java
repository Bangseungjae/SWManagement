package com.example.swmanagement.controller;

import com.example.swmanagement.dto.chat.ChatRequestDto;
import com.example.swmanagement.dto.chat.ChatResponseDto;
import com.example.swmanagement.service.ChatService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @ApiOperation(value = "이슈에 채팅을 입력하는 api, path의 id = issue의 id")
    @PostMapping("/chat/{id}")
    public ResponseEntity<ChatResponseDto> createChat(@RequestBody ChatRequestDto dto, @PathVariable("id") Long issueId) {
        ChatResponseDto chatDto = chatService.createChat(issueId, dto);
        return ResponseEntity.ok().body(chatDto);
    }

    @ApiOperation(value = "이슈의 채팅을 가져오는 api, path의 id = Issue 의 id")
    @GetMapping("/chat/{id}")
    public ResponseEntity<List<ChatResponseDto>> getChats(@PathVariable("id") Long issueId) {
        List<ChatResponseDto> chats = chatService.getChats(issueId);
        return ResponseEntity.ok().body(chats);
    }
}
