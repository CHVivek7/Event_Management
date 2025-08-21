package com.eventmanagement.repository;

import com.eventmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository for Event entity
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCustomerId(Long customerId);
}
