package com.example.swmanagement.dto.issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueResponseDto {

    private Long id;
    private String content;
    private String username;
}
