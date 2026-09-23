package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Customer;
import com.example.grainsilomanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // Create customer
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}