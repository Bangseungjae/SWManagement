package com.example.swmanagement.controller;

import com.example.swmanagement.dto.invitation.InvitationDto;
import com.example.swmanagement.dto.invitation.InvitationRequestDto;
import com.example.swmanagement.service.InvitationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "초대 API")
public class InvitationController {

    private final InvitationService invitationService;

    @ApiOperation(value = "프로젝트에 사용자 초대하기")
    @PostMapping("/invite")
    public ResponseEntity invite(@RequestBody @Valid InvitationRequestDto dto) {
        invitationService.invite(dto.getProjectId(), dto.getEmail());
        return ResponseEntity.ok().body(null);
    }

    /**
     * 유저의 초대 정보를 보여준다.
     */
    @ApiOperation(value = "해당 이메일에 있는 프로젝트 초대들")
    @GetMapping("/invitations")
    public ResponseEntity<List<InvitationDto>> findMyInvitations(@RequestParam String email) {
        List<InvitationDto> myInvitation = invitationService.findMyInvitation(email);
        return ResponseEntity.ok().body(myInvitation);
    }

    @ApiOperation(value = "초대 수락하기")
    @PostMapping("/invitation/accept/{id}")
    public ResponseEntity acceptInvitation(@PathVariable("id") Long invitationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        invitationService.accept(authentication.getName(), invitationId);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "초대 거절하기")
    @DeleteMapping("/invitation/{invitationId}")
    public ResponseEntity deleteInvitation(@PathVariable("invitationId") Long invitationId) {
        invitationService.deleteInvitation(invitationId);
        return ResponseEntity.ok().body(null);
    }
}
