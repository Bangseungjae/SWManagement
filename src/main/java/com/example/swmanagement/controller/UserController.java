package com.example.swmanagement.controller;

import com.example.swmanagement.dto.LoginRequest;
import com.example.swmanagement.dto.ResponseLogin;
import com.example.swmanagement.security.service.AuthService;
import com.example.swmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody LoginRequest loginRequest) {
        userService.signUp(loginRequest);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody LoginRequest loginRequest) {
        ResponseLogin authenticate = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().body(authenticate);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("healthy");
    }
}
