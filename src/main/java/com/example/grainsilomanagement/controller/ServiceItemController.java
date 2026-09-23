package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.ServiceItem;
import com.example.grainsilomanagement.service.ServiceItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-items")
@CrossOrigin
public class ServiceItemController {

    private final ServiceItemService service;

    public ServiceItemController(ServiceItemService service) {
        this.service = service;
    }

    // Create service item
    @PostMapping
    public ServiceItem createServiceItem(@RequestBody ServiceItem serviceItem) {
        return service.createServiceItem(serviceItem);
    }

    // Get all service items
    @GetMapping
    public List<ServiceItem> getAllServiceItems() {
        return service.getAllServiceItems();
    }

    // Get service item by ID
    @GetMapping("/{id}")
    public ServiceItem getServiceItemById(@PathVariable Long id) {
        return service.getServiceItemById(id);
    }

    // Delete service item
    @DeleteMapping("/{id}")
    public String deleteServiceItem(@PathVariable Long id) {
        service.deleteServiceItem(id);
        return "Service item deleted successfully";
    }
}