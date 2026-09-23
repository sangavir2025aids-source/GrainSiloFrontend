package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.Account;
import com.example.grainsilomanagement.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return service.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
        return "Account deleted successfully";
    }
}