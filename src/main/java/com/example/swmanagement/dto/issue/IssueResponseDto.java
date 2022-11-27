package com.example.swmanagement.dto.issue;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssueResponseDto {

    private Long id;
    private String content;
    private String username;


    @QueryProjection
    public IssueResponseDto(Long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.username = username;
    }
}
