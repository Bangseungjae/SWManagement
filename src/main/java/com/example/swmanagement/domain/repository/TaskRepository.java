package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
