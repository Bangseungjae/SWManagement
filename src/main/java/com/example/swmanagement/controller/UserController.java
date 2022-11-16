package com.example.swmanagement.controller;

import com.example.swmanagement.dto.LoginRequest;
import com.example.swmanagement.dto.ResponseLogin;
import com.example.swmanagement.security.service.AuthService;
import com.example.swmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "로그인 API")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @ApiOperation(value = "회원가입")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "email"),
//            @ApiImplicitParam(name = "username"),
//            @ApiImplicitParam(name = "password")
//    })
    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody LoginRequest loginRequest) {
        userService.signUp(loginRequest);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "로그인")
    @GetMapping("/login")
    public ResponseEntity<ResponseLogin> login(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseLogin authenticate = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().body(authenticate);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("healthy1");
    }
}
