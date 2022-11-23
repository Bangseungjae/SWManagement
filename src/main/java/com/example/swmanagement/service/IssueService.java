package com.example.swmanagement.service;

import com.example.swmanagement.domain.Issue;
import com.example.swmanagement.domain.Project;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.IssueRepository;
import com.example.swmanagement.domain.repository.ProjectRepository;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.issue.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public IssueResponseDto createIssue(Long projectId, String content) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id의 프로젝트가 없습니다."));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 없습니다."));

        Issue issue = Issue.builder()
                .content(content)
                .project(project)
                .user(user)
                .build();

        Issue savedIssue = issueRepository.save(issue);

        project.getIssues().add(savedIssue);
        user.getIssues().add(savedIssue);

        IssueResponseDto issueResponseDto = new IssueResponseDto(savedIssue.getId(), savedIssue.getContent(), savedIssue.getUser().getUsername());
        return issueResponseDto;
    }
}
