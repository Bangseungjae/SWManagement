package com.example.swmanagement.service;

import com.example.swmanagement.domain.Invitation;
import com.example.swmanagement.domain.Project;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.InvitationRepository;
import com.example.swmanagement.domain.repository.ProjectRepository;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.invitation.InvitationDto;
import com.example.swmanagement.exception.MissMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

@Slf4j
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

    public void accept(String email, Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 초대가 없습니다."));

        User userByEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자가 없습니다."));
        User user = invitation.getUser();

        Project project = invitation.getProject();
        project.getUser().add(user);

        if (!user.getEmail().equals(userByEmail.getEmail())) {
            throw new MissMatchException("사용자 이메일과 요청하신 초대와 일치하지 안습니다.", 400);
        }
        user.getProjects().add(project);
        invitationRepository.delete(invitation);
    }

    public void deleteInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 초대가 존재하지 않습니다."));
        invitationRepository.delete(invitation);
    }
}
