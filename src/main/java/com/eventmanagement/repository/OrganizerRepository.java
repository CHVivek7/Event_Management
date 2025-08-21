package com.eventmanagement.repository;

import com.eventmanagement.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository for Organizer entity
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Optional<Organizer> findByUsername(String username);
}
