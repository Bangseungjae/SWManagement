package com.example.swmanagement.dto.board;

import com.example.swmanagement.domain.PriorityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String name;
    private Integer score;
    private PriorityStatus priorityStatus;
    private String description;
}
