package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.QIssue;
import com.example.swmanagement.dto.issue.IssueResponseDto;
import com.example.swmanagement.dto.issue.QIssueResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.swmanagement.domain.QIssue.*;

@Repository
@RequiredArgsConstructor
public class IssueQueryDsl {

    private final JPAQueryFactory queryFactory;

    public List<IssueResponseDto> getIssueByProjectId(Long projectId) {
        List<IssueResponseDto> fetch = queryFactory.select(new QIssueResponseDto(issue.id, issue.content, issue.user.username))
                .from(issue)
                .where(issue.project.id.eq(projectId))
                .fetch();
        return fetch;
    }
}
