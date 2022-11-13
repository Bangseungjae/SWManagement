package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
