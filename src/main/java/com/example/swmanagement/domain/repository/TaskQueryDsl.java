package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.TaskStatus;
import com.example.swmanagement.dto.task.QTaskResponseDto;
import com.example.swmanagement.dto.task.TaskResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.swmanagement.domain.QTask.*;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    public Long findScoreSumByProjectId(Long projectId) {
        Long sum = queryFactory.select(task.score.sum())
                .from(task)
                .where(task.project.id.eq(projectId))
                .fetchOne();

//        log.info("task sum : {}", sum);
        if (sum == null) {
            sum = 0L;
        }
        return sum;
    }
}
