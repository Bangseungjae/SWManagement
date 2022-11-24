package com.example.swmanagement.controller;

import com.example.swmanagement.dto.issue.IssueRequestDto;
import com.example.swmanagement.dto.issue.IssueResponseDto;
import com.example.swmanagement.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "이슈 API")
public class IssueController {

    private final IssueService issueService;

    @ApiOperation(value = "issue를 생성합니다. id = project id")
    @PostMapping("/issue/{id}")
    public ResponseEntity<IssueResponseDto> createIssue(@PathVariable("id") Long id, @Valid @RequestBody IssueRequestDto dto) {
        IssueResponseDto issue = issueService.createIssue(id, dto.getContent());
        return ResponseEntity.ok().body(issue);
    }

    @ApiOperation(value = "issue들을 봅니다. id = project id")
    @GetMapping("/issue/{id}")
    public ResponseEntity<List<IssueResponseDto>> issues(@PathVariable("id") Long id) {
        List<IssueResponseDto> issues = issueService.getIssues(id);
        return ResponseEntity.ok().body(issues);
    }


    @ApiOperation(value = "issue를 삭제합니다. id = issue id")
    @DeleteMapping("/issue/{id}")
    public ResponseEntity deleteIssue(@PathVariable("id") Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.ok().body(null);
    }

}
