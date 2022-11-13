package com.example.swmanagement.service;

import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(LoginRequest loginRequest) {
        Optional<User> byEmail = userRepository.findByEmail(loginRequest.getEmail());
        if (byEmail.isPresent()) {
            throw new IllegalArgumentException("존재하는 이메일 입니다.");
        }
        String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
        User user = User.builder()
                .email(loginRequest.getEmail())
                .password(encodedPassword)
                .username(loginRequest.getUsername())
                .build();
        userRepository.save(user);
    }

    public void delete(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        userRepository.delete(user);
    }
}
