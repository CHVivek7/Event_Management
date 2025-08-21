package com.eventmanagement.service;

import com.eventmanagement.model.Event;
import com.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    // Register a new event
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get events by customer ID
    public List<Event> getEventsByCustomerId(Long customerId) {
        return eventRepository.findByCustomerId(customerId);
    }

    // Find event by ID
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    // Delete (soft delete) event by ID
    public void cancelEvent(Long eventId, String cancellationReason) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            event.setCancellationReason(cancellationReason);
            eventRepository.save(event);
        }
    }
}
