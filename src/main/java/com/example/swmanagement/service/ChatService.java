package com.example.swmanagement.service;

import com.example.swmanagement.domain.Chat;
import com.example.swmanagement.domain.Issue;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.ChatRepository;
import com.example.swmanagement.domain.repository.IssueRepository;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.chat.ChatRequestDto;
import com.example.swmanagement.dto.chat.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRepository chatRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public ChatResponseDto createChat(Long issueId, ChatRequestDto dto) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 issue가 없습니다."));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 email의 유저가 없습니다."));

        Chat chat = Chat.builder()
                .user(user)
                .content(dto.getContent())
                .issue(issue)
                .build();

        Chat savedChat = chatRepository.save(chat);

        user.getChats().add(savedChat);
        issue.getChats().add(savedChat);

        return new ChatResponseDto(savedChat.getId(), savedChat.getContent());
    }

    @Transactional(readOnly = true)
    public List<ChatResponseDto> getChats(Long issueId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 issue가 없습니다."));

        List<Chat> chats = issue.getChats();

        List<ChatResponseDto> dtos = new ArrayList<>();

        for (Chat chat : chats) {
            dtos.add(new ChatResponseDto(chat.getId(), chat.getContent()));
        }
        dtos.sort((it1, it2) -> (int) (it1.getId() - it2.getId()));
        return dtos;
    }
}
