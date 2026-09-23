package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.SalesOrder;
import com.example.grainsilomanagement.repository.SalesOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesOrderService {

    private final SalesOrderRepository repository;

    public SalesOrderService(SalesOrderRepository repository) {
        this.repository = repository;
    }

    // Create sales order
    public SalesOrder createSalesOrder(SalesOrder salesOrder) {

        salesOrder.setOrderDate(LocalDateTime.now());

        if (salesOrder.getStatus() == null ||
                salesOrder.getStatus().isBlank()) {
            salesOrder.setStatus("CONFIRMED");
        }

        return repository.save(salesOrder);
    }

    // Get all sales orders
    public List<SalesOrder> getAllSalesOrders() {
        return repository.findAll();
    }

    // Get sales order by ID
    public SalesOrder getSalesOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete sales order
    public void deleteSalesOrder(Long id) {
        repository.deleteById(id);
    }
}