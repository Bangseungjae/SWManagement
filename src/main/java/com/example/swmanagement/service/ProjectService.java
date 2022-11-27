package com.example.swmanagement.service;

import com.example.swmanagement.domain.Invitation;
import com.example.swmanagement.domain.Project;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.*;
import com.example.swmanagement.dto.project.ExpectFinalDate;
import com.example.swmanagement.dto.project.ProjectRequestDto;
import com.example.swmanagement.dto.project.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final InvitationRepository invitationRepository;
    private final TaskQueryDsl taskQueryDsl;
    private final BoardQueryDsl boardQueryDsl;

    public void registerProject(ProjectRequestDto projectRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        List<String> invitationEmail1 = projectRequestDto.getInvitationEmail();
        for (String s : invitationEmail1) {
            userRepository.findByEmail(s)
                    .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        }

        Project project = Project.builder()
                .title(projectRequestDto.getTitle())
                .description(projectRequestDto.getDescription())
                .build();

        project.getUser().add(user);
        projectRepository.save(project);

        user.getProjects().add(project);

        List<String> invitationEmail = projectRequestDto.getInvitationEmail();
        for (String ie : invitationEmail) {
            Optional<User> byEmail = userRepository.findByEmail(ie);
            if (byEmail.isPresent()) {
                Invitation invitation = Invitation.builder()
                        .user(byEmail.get())
                        .project(project)
                        .build();
                invitationRepository.save(invitation);
            }
        }
    }

    public Project findById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        return project;
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDto> findProjects(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        List<Project> projects = user.getProjects();

        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();

        for (Project project : projects) {
            ProjectResponseDto dto = ProjectResponseDto.builder()
                    .id(project.getId())
                    .title(project.getTitle())
                    .description(project.getDescription())
                    .build();
            projectResponseDtos.add(dto);
        }
        return projectResponseDtos;

    }

    public void deleteProject(String email, Long projectId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 email의 사용자가 없습니다."));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 프로젝트가 없습니다."));


        user.getProjects().remove(project);
        project.getUser().remove(user);
    }

    public ExpectFinalDate findFinalDate(Long projectId) {
        Long score1 = taskQueryDsl.findScoreSumByProjectId(projectId);
        Long score2 = boardQueryDsl.sumScoreByProjectId(projectId);
        Long score = score1 + score2;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusDays(score);

        return new ExpectFinalDate(localDateTime.getYear(), localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth());
    }
}
