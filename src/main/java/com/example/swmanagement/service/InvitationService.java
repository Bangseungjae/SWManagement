package com.example.swmanagement.service;

import com.example.swmanagement.domain.Invitation;
import com.example.swmanagement.domain.Project;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.InvitationRepository;
import com.example.swmanagement.domain.repository.ProjectRepository;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.invitation.InvitationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    /**
     * 사용자 이메일로 자신에게 온 초대정보 확인
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
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

    public void invite(Long projectId, String email) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자가 존재하지 않습니다."));

        Invitation invitation = Invitation.builder()
                .project(project)
                .user(user)
                .build();

        invitationRepository.save(invitation);
    }
}
