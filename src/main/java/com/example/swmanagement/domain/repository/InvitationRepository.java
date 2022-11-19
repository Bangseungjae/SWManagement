package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
