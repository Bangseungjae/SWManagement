package com.example.swmanagement.controller;

import com.example.swmanagement.dto.invitation.InvitationDto;
import com.example.swmanagement.dto.user.LoginRequest;
import com.example.swmanagement.dto.user.ResponseLogin;
import com.example.swmanagement.dto.user.UserByProjectDto;
import com.example.swmanagement.security.service.AuthService;
import com.example.swmanagement.service.InvitationService;
import com.example.swmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "로그인 API")
public class UserController {
    private final AuthService authService;
    private final UserService userService;
    private final InvitationService invitationService;

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
    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseLogin authenticate = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().body(authenticate);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("healthy6");
    }


    @ApiOperation(value = "해당 프로젝트에 참여하는 유저입니다. projectId = project의 아이가")
    @GetMapping("/users/{projectId}")
    public ResponseEntity<List<UserByProjectDto>> findByProjectId(@PathVariable("projectId") Long projectId) {
        List<UserByProjectDto> userByProject = userService.findByProjectId(projectId);
        return ResponseEntity.ok().body(userByProject);
    }
}
