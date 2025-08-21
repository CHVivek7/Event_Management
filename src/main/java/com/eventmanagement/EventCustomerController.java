package com.eventmanagement;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.Customer;
import com.eventmanagement.service.EventService;
import com.eventmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class EventCustomerController {
    // View own profile information
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        Optional<Customer> customerOpt = customerService.findByUsername(principal.getName());
        if (!customerOpt.isPresent()) {
            return ResponseEntity.status(401).body("Customer not found");
        }
        Customer customer = customerOpt.get();
    Map<String, Object> profile = new java.util.HashMap<>();
    profile.put("id", customer.getId());
    profile.put("username", customer.getUsername());
    profile.put("email", customer.getEmail());
    profile.put("role", customer.getRole());
    profile.put("isBlocked", customer.isBlocked());
    // Do not include password in profile response
    return ResponseEntity.ok(profile);
    }
    @Autowired
    private EventService eventService;
    @Autowired
    private CustomerService customerService;

    // Register a new event
    @PostMapping("/register")
    public ResponseEntity<?> registerEvent(@RequestBody Map<String, Object> request, Principal principal) {
        Optional<Customer> customerOpt = customerService.findByUsername(principal.getName());
        if (!customerOpt.isPresent()) {
            return ResponseEntity.status(401).body("Customer not found");
        }
        Customer customer = customerOpt.get();
        if (customer.isBlocked()) {
            return ResponseEntity.badRequest().body("Customer is blocked from booking events");
        }
        Event event = new Event();
        event.setCustomer(customer);
        event.setType((String) request.get("type"));
        event.setName((String) request.get("name"));
        event.setExpectedAttendance((int) request.get("expectedAttendance"));
        event.setBudget(Double.parseDouble(request.get("budget").toString()));
        event.setLocation((String) request.get("location"));
        event.setEventTime(java.time.LocalDateTime.parse((String) request.get("eventTime")));
        eventService.saveEvent(event);
        return ResponseEntity.ok("Event registered successfully");
    }

    // Update an existing event
    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody Map<String, Object> request, Principal principal) {
        Optional<Event> eventOpt = eventService.findById(eventId);
        if (!eventOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Event not found");
        }
        Event event = eventOpt.get();
        if (!event.getCustomer().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(403).body("Illegal access: It is against company policy to access or modify another customer's event details.");
        }
        if (request.containsKey("type")) event.setType((String) request.get("type"));
        if (request.containsKey("name")) event.setName((String) request.get("name"));
        if (request.containsKey("expectedAttendance")) event.setExpectedAttendance((int) request.get("expectedAttendance"));
        if (request.containsKey("budget")) event.setBudget(Double.parseDouble(request.get("budget").toString()));
        if (request.containsKey("location")) event.setLocation((String) request.get("location"));
        if (request.containsKey("eventTime")) event.setEventTime(java.time.LocalDateTime.parse((String) request.get("eventTime")));
        eventService.saveEvent(event);
        return ResponseEntity.ok("Event updated successfully");
    }

    // Get details of a specific event
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Long eventId, Principal principal) {
        Optional<Event> eventOpt = eventService.findById(eventId);
        if (!eventOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Event not found");
        }
        Event event = eventOpt.get();
        if (!event.getCustomer().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(403).body("Illegal access: It is against company policy to view another customer's event details.");
        }
        return ResponseEntity.ok(event);
    }

    // Cancel (soft delete) an event
    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> cancelEvent(@PathVariable Long eventId, @RequestBody Map<String, String> request, Principal principal) {
        Optional<Event> eventOpt = eventService.findById(eventId);
        if (!eventOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Event not found");
        }
        Event event = eventOpt.get();
        if (!event.getCustomer().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(403).body("Illegal access: It is against company policy to cancel another customer's event.");
        }
        String reason = request.get("cancellationReason");
        eventService.cancelEvent(eventId, reason);
        return ResponseEntity.ok("Event cancelled successfully");
    }
}
