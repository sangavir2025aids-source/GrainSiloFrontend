package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Account;
import com.example.grainsilomanagement.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(Account account) {

        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }

        return repository.save(account);
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account getAccountById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}