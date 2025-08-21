package com.eventmanagement.repository;

import com.eventmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository for Customer entity
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
}
