package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.PurchaseOrder;
import com.example.grainsilomanagement.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseOrder createPurchaseOrder(
            @RequestBody PurchaseOrder purchaseOrder) {
        return service.createPurchaseOrder(purchaseOrder);
    }

    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return service.getAllPurchaseOrders();
    }

    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderById(@PathVariable Long id) {
        return service.getPurchaseOrderById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePurchaseOrder(@PathVariable Long id) {
        service.deletePurchaseOrder(id);
        return "Purchase order deleted successfully";
    }
}