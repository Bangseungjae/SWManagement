package com.example.swmanagement.service;

import com.example.swmanagement.domain.Board;
import com.example.swmanagement.domain.Task;
import com.example.swmanagement.domain.TaskStatus;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.BoardRepository;
import com.example.swmanagement.domain.repository.TaskQueryDsl;
import com.example.swmanagement.domain.repository.TaskRepository;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.task.TaskRequestDto;
import com.example.swmanagement.dto.task.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final TaskQueryDsl taskQueryDsl;

    public TaskResponseDto createTask(Long boardId, TaskRequestDto dto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Board가 없습니다."));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Task task = Task.builder()
                .taskName(board.getName())
                .description(board.getDescription())
                .score(board.getScore())
                .taskStatus(dto.getTaskStatus())
                .user(user)
                .project(board.getProject())
                .build();
        board.getProject().getTasks().add(task);

        Task savedTask = taskRepository.save(task);

        boardRepository.deleteById(boardId);

        TaskResponseDto taskResponseDto =
                new TaskResponseDto(savedTask.getId(),
                        savedTask.getTaskName(),
                        savedTask.getScore(),
                        savedTask.getDescription(),
                        savedTask.getTaskStatus());

        return taskResponseDto;
    }

    public void taskStatusChange(Long id, TaskStatus status) {
        taskQueryDsl.changeStatus(status, id);
    }

    public List<TaskResponseDto> selectTasksByStatus(TaskStatus taskStatus, Long projectId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        List<TaskResponseDto> taskResponseDtos = taskQueryDsl.selectTasks(taskStatus, projectId, user.getId());
        return taskResponseDtos;
    }

    public List<TaskResponseDto> selectTaskByUserAndProjectAndStatus(Long projectId, Long userId, TaskStatus status) {
        return taskQueryDsl.selectTasks(status, projectId, userId);
    }
}

