package com.example.swmanagement.controller;

import com.example.swmanagement.dto.issue.IssueRequestDto;
import com.example.swmanagement.dto.issue.IssueResponseDto;
import com.example.swmanagement.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

}
