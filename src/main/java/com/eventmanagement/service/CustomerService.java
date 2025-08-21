package com.eventmanagement.service;

import com.eventmanagement.model.Customer;
import com.eventmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // Register a new customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Find customer by username
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    // Find customer by email
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Find customer by ID
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    // Block a customer
    public void blockCustomer(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setBlocked(true);
            customerRepository.save(customer);
        }
    }

    // Unblock a customer
    public void unblockCustomer(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setBlocked(false);
            customerRepository.save(customer);
        }
    }
}
