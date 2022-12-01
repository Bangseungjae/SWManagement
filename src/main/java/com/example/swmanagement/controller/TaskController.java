package com.example.swmanagement.controller;

import com.example.swmanagement.domain.TaskStatus;
import com.example.swmanagement.dto.task.TaskRequestDto;
import com.example.swmanagement.dto.task.TaskResponseDto;
import com.example.swmanagement.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Task API입니다.")
public class TaskController {
    private final TaskService taskService;

    @ApiOperation(value = "id = board의 id   Task생성 api status = TODO, IN_PROGRESS, RESOLVED")
    @PostMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto dto, @PathVariable("id") Long boardId) {
        TaskResponseDto taskResponseDto = taskService.createTask(boardId, dto);
        return ResponseEntity.ok().body(taskResponseDto);
    }

    @ApiOperation(value = "Task의 진행 상태 변경, id = task의 id")
    @PutMapping("/task/{id}")
    public ResponseEntity chageTaskStatus(@PathVariable("id") Long id, TaskRequestDto dto) {
        taskService.taskStatusChange(id, dto);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "task상태별로 보기, id = Project id")
    @GetMapping("/task/{id}")
    public ResponseEntity<List<TaskResponseDto>> tasks(@RequestParam TaskStatus status, @PathVariable("id") Long id) {

        List<TaskResponseDto> taskResponseDtos = taskService.selectTasksByStatus(status, id);
        return ResponseEntity.ok().body(taskResponseDtos);
    }
}
