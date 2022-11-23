package com.example.swmanagement.dto.task;

import com.example.swmanagement.domain.TaskStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String TaskName;
    private Long score;
    private String description;
    private TaskStatus taskStatus;

    @QueryProjection
    public TaskResponseDto(Long id, String taskName, Long score, String description, TaskStatus taskStatus) {
        this.id = id;
        TaskName = taskName;
        this.score = score;
        this.description = description;
        this.taskStatus = taskStatus;
    }
}
