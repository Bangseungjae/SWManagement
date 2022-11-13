package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
