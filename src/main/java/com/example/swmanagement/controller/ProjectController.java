package com.example.swmanagement.controller;

import com.example.swmanagement.dto.project.ProjectRequestDto;
import com.example.swmanagement.dto.project.ProjectResponseDto;
import com.example.swmanagement.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "해당 email의 프로제트들 정보 요청")
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDto>> findProjects(String email) {
        List<ProjectResponseDto> projectResponseDtos = projectService.findProjects(email);
        return ResponseEntity.ok().body(projectResponseDtos);
    }

    @ApiOperation(value = "해당 유저가 해당 프로젝트 아이디의 프로젝트에서 탈퇴합니다. id = project의 id")
    @DeleteMapping("/project/{id}")
    public ResponseEntity deleteProject(@PathVariable("id") Long projectId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        projectService.deleteProject(email, projectId);
        return ResponseEntity.ok().body(null);
    }
}
