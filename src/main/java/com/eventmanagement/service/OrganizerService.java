package com.eventmanagement.service;

import com.eventmanagement.model.Organizer;
import com.eventmanagement.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;

    // Register a new organizer
    public Organizer saveOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    // Find organizer by username
    public Optional<Organizer> findByUsername(String username) {
        return organizerRepository.findByUsername(username);
    }

    // Find organizer by ID
    public Optional<Organizer> findById(Long id) {
        return organizerRepository.findById(id);
    }
}
