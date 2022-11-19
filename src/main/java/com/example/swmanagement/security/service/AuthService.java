package com.example.swmanagement.security.service;

import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.security.TokenProvider;
import com.example.swmanagement.dto.user.ResponseLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseLogin authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다." + email));
        matchPassword(password, user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = tokenProvider.createToken(authenticate);
        return ResponseLogin.builder()
                .accessToken(token)
                .username(user.getUsername())
                .build();
    }

    private void matchPassword(String password, User user) {
        boolean matchs = passwordEncoder.matches(password, user.getPassword());

        if (!matchs) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
    }
}
