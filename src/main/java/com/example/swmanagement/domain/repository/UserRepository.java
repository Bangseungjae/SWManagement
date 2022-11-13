package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
