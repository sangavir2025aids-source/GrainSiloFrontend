package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.AnalyticAccount;
import com.example.grainsilomanagement.repository.AnalyticAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticAccountService {

    private final AnalyticAccountRepository repository;

    public AnalyticAccountService(AnalyticAccountRepository repository) {
        this.repository = repository;
    }

    public AnalyticAccount createAccount(AnalyticAccount account) {
        return repository.save(account);
    }

    public List<AnalyticAccount> getAllAccounts() {
        return repository.findAll();
    }

    public AnalyticAccount getAccountById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}