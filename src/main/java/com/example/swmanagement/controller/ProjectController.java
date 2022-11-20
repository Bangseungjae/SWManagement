package com.example.swmanagement.controller;

import com.example.swmanagement.dto.project.ProjectRequestDto;
import com.example.swmanagement.dto.project.ProjectResponseDto;
import com.example.swmanagement.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "프로젝트 API")
public class ProjectController {

    private final ProjectService projectService;

    @ApiOperation(value = "프로젝트 생성")
    @PostMapping("/project")
    public ResponseEntity createProject(@RequestBody ProjectRequestDto dto) {
        projectService.registerProject(dto);
        return ResponseEntity.ok().body(null);
    }

    public ResponseEntity<List<ProjectResponseDto>> findProjects(String email) {
        List<ProjectResponseDto> projectResponseDtos = projectService.findProjects(email);
        return ResponseEntity.ok().body(projectResponseDtos);
    }
}
