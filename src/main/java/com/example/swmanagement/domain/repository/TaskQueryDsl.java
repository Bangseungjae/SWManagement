package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.TaskStatus;
import com.example.swmanagement.dto.task.QTaskResponseDto;
import com.example.swmanagement.dto.task.TaskResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.swmanagement.domain.QTask.*;

@Repository
@RequiredArgsConstructor
public class TaskQueryDsl {

    private final JPAQueryFactory queryFactory;

    public void changeStatus(TaskStatus taskStatus, Long taskId) {
        queryFactory.update(task)
                .set(task.taskStatus, taskStatus)
                .where(task.id.eq(taskId))
                .execute();
    }

    public List<TaskResponseDto> selectTasks(TaskStatus taskStatus, Long projectid) {
        List<TaskResponseDto> dtos =
                queryFactory.select(new QTaskResponseDto(task.id, task.taskName, task.score, task.description, task.taskStatus))
                .from(task)
                .where(task.taskStatus.eq(taskStatus).and(task.project.id.eq(projectid)))
                .fetch();
        return dtos;
    }

}
