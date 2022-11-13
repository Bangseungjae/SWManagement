package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
