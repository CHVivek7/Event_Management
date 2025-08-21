package com.eventmanagement;

import com.eventmanagement.dto.CustomerRegisterRequest;
import com.eventmanagement.dto.OrganizerRegisterRequest;
import com.eventmanagement.dto.LoginRequest;
import com.eventmanagement.model.Customer;
import com.eventmanagement.model.Organizer;
import com.eventmanagement.security.JwtUtil;
import com.eventmanagement.security.TokenBlacklist;
import com.eventmanagement.service.CustomerService;
import com.eventmanagement.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    // Register a new customer
    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegisterRequest request) {
        // Check if username or email already exists
        if (customerService.findByUsername(request.username).isPresent() || customerService.findByEmail(request.email).isPresent()) {
            return ResponseEntity.badRequest().body("Username or email already exists");
        }
        Customer customer = new Customer();
        customer.setUsername(request.username);
        customer.setPassword(passwordEncoder.encode(request.password));
        customer.setEmail(request.email);
        customer.setRole("ROLE_CUSTOMER");
        customer.setBlocked(false);
        customerService.saveCustomer(customer);
        return ResponseEntity.ok("Customer registered successfully");
    }

    // Register a new organizer
    @PostMapping("/register/organizer")
    public ResponseEntity<?> registerOrganizer(@RequestBody OrganizerRegisterRequest request) {
        if (organizerService.findByUsername(request.username).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        Organizer organizer = new Organizer();
        organizer.setUsername(request.username);
        organizer.setPassword(passwordEncoder.encode(request.password));
        organizer.setRole("ROLE_ORGANIZER");
        organizerService.saveOrganizer(organizer);
        return ResponseEntity.ok("Organizer registered successfully");
    }

    // Login for customer or organizer
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Try to find user as customer
        Optional<Customer> customerOpt = customerService.findByUsername(request.username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (passwordEncoder.matches(request.password, customer.getPassword())) {
                String token = jwtUtil.generateToken(customer.getUsername(), customer.getRole());
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", customer.getRole());
                return ResponseEntity.ok(response);
            }
        }
        // Try to find user as organizer
        Optional<Organizer> organizerOpt = organizerService.findByUsername(request.username);
        if (organizerOpt.isPresent()) {
            Organizer organizer = organizerOpt.get();
            if (passwordEncoder.matches(request.password, organizer.getPassword())) {
                String token = jwtUtil.generateToken(organizer.getUsername(), organizer.getRole());
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", organizer.getRole());
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    // Logout endpoint for JWT-based authentication
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        // Extract token from Authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            TokenBlacklist.blacklistToken(token);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logged out successfully. Please delete the token on the client side.");
        return ResponseEntity.ok(response);
    }
}
