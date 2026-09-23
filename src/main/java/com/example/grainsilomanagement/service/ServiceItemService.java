package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.ServiceItem;
import com.example.grainsilomanagement.repository.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemService {

    private final ServiceItemRepository repository;

    public ServiceItemService(ServiceItemRepository repository) {
        this.repository = repository;
    }

    // Create service item
    public ServiceItem createServiceItem(ServiceItem serviceItem) {
        return repository.save(serviceItem);
    }

    // Get all service items
    public List<ServiceItem> getAllServiceItems() {
        return repository.findAll();
    }

    // Get service item by ID
    public ServiceItem getServiceItemById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete service item
    public void deleteServiceItem(Long id) {
        repository.deleteById(id);
    }
}