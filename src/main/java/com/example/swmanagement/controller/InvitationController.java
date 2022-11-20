package com.example.swmanagement.controller;

import com.example.swmanagement.dto.invitation.InvitationRequestDto;
import com.example.swmanagement.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping("/invite")
    public ResponseEntity invite(InvitationRequestDto dto) {
        invitationService.invite(dto.getProjectId(), dto.getEmail());
        return ResponseEntity.ok().body(null);
    }
}
