package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
