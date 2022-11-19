package com.example.swmanagement.controller;

import com.example.swmanagement.dto.project.ProjectRequestDto;
import com.example.swmanagement.dto.project.ProjectResponseDto;
import com.example.swmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity createProject(@RequestBody ProjectRequestDto dto) {
        projectService.registerProject(dto);
        return ResponseEntity.ok().body(null);
    }
}
