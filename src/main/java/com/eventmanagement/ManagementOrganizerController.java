package com.eventmanagement;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.Customer;
import com.eventmanagement.service.EventService;
import com.eventmanagement.service.CustomerService;
import com.eventmanagement.service.OrganizerService;
import com.eventmanagement.model.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.security.Principal;

@RestController
@RequestMapping("/api/organizer")
public class ManagementOrganizerController {
    @Autowired
    private EventService eventService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrganizerService organizerService;
    // Get organizer profile
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        Optional<Organizer> organizerOpt = organizerService.findByUsername(principal.getName());
        if (!organizerOpt.isPresent()) {
            return ResponseEntity.status(401).body("Organizer not found");
        }
        Organizer organizer = organizerOpt.get();
        java.util.Map<String, Object> profile = new java.util.HashMap<>();
        profile.put("id", organizer.getId());
        profile.put("username", organizer.getUsername());
        profile.put("role", organizer.getRole());
        // Do not include password in profile response
        return ResponseEntity.ok(profile);
    }

    // Get all events
    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Get details of a specific event
    @GetMapping("/events/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Long eventId) {
        Optional<Event> eventOpt = eventService.findById(eventId);
        if (!eventOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Event not found");
        }
        return ResponseEntity.ok(eventOpt.get());
    }

    // Get all customers
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Block a customer
    @PutMapping("/customers/{customerId}/block")
    public ResponseEntity<?> blockCustomer(@PathVariable Long customerId) {
        customerService.blockCustomer(customerId);
        return ResponseEntity.ok("Customer blocked successfully");
    }

    // Unblock a customer
    @PutMapping("/customers/{customerId}/unblock")
    public ResponseEntity<?> unblockCustomer(@PathVariable Long customerId) {
        customerService.unblockCustomer(customerId);
        return ResponseEntity.ok("Customer unblocked successfully");
    }

    // Cancel (soft delete) any event
    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<?> cancelEvent(@PathVariable Long eventId, @RequestBody Map<String, String> request) {
        String reason = request.get("cancellationReason");
        eventService.cancelEvent(eventId, reason);
        return ResponseEntity.ok("Event cancelled by organizer");
    }
}
