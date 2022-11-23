package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.QTask;
import com.example.swmanagement.domain.TaskStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
