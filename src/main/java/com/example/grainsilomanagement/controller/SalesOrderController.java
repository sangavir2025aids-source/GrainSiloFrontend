package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.SalesOrder;
import com.example.grainsilomanagement.service.SalesOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
@CrossOrigin
public class SalesOrderController {

    private final SalesOrderService service;

    public SalesOrderController(SalesOrderService service) {
        this.service = service;
    }

    // Create sales order
    @PostMapping
    public SalesOrder createSalesOrder(@RequestBody SalesOrder salesOrder) {
        return service.createSalesOrder(salesOrder);
    }

    // Get all sales orders
    @GetMapping
    public List<SalesOrder> getAllSalesOrders() {
        return service.getAllSalesOrders();
    }

    // Get sales order by ID
    @GetMapping("/{id}")
    public SalesOrder getSalesOrderById(@PathVariable Long id) {
        return service.getSalesOrderById(id);
    }

    // Delete sales order
    @DeleteMapping("/{id}")
    public String deleteSalesOrder(@PathVariable Long id) {
        service.deleteSalesOrder(id);
        return "Sales order deleted successfully";
    }
}