package com.example.swmanagement.dto.task;

import com.example.swmanagement.domain.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
    private TaskStatus taskStatus;
}
