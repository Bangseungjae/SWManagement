package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Invitation;
import com.example.swmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findByUser(User user);
}
