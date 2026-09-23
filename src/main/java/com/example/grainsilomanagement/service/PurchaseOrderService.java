package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.PurchaseOrder;
import com.example.grainsilomanagement.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository repository;

    public PurchaseOrderService(PurchaseOrderRepository repository) {
        this.repository = repository;
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {

        purchaseOrder.setOrderDate(LocalDateTime.now());

        if (purchaseOrder.getStatus() == null ||
                purchaseOrder.getStatus().isBlank()) {
            purchaseOrder.setStatus("CONFIRMED");
        }

        return repository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return repository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletePurchaseOrder(Long id) {
        repository.deleteById(id);
    }
}