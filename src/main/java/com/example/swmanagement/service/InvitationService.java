package com.example.swmanagement.service;

import com.example.swmanagement.domain.Invitation;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.InvitationRepository;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.invitation.InvitationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;

    public List<InvitationDto> findMyInvitation(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일 입니다."));
        List<Invitation> invitations = invitationRepository.findByUser(user);

        List<InvitationDto> invitationDtos = new ArrayList<>();
        for (Invitation invitation : invitations) {
            InvitationDto dto = new InvitationDto(invitation.getId(), invitation.getProject().getTitle());
            invitationDtos.add(dto);
        }

        return invitationDtos;
    }
}
