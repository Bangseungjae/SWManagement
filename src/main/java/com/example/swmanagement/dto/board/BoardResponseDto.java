package com.example.swmanagement.dto.board;

import com.example.swmanagement.domain.PriorityStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String name;
    private Long score;
    private PriorityStatus priorityStatus;
    private String description;
    
    @QueryProjection
    public BoardResponseDto(Long id, String name, Long score, PriorityStatus priorityStatus, String description) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.priorityStatus = priorityStatus;
        this.description = description;
    }
}
