package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.AnalyticAccount;
import com.example.grainsilomanagement.service.AnalyticAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytic-accounts")
@CrossOrigin
public class AnalyticAccountController {

    private final AnalyticAccountService service;

    public AnalyticAccountController(AnalyticAccountService service) {
        this.service = service;
    }

    @PostMapping
    public AnalyticAccount createAccount(@RequestBody AnalyticAccount account) {
        return service.createAccount(account);
    }

    @GetMapping
    public List<AnalyticAccount> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AnalyticAccount getAccountById(@PathVariable Long id) {
        return service.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
        return "Analytic account deleted successfully";
    }
}