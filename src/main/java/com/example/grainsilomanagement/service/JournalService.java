package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.Journal;
import com.example.grainsilomanagement.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalService {

    private final JournalRepository repository;

    public JournalService(JournalRepository repository) {
        this.repository = repository;
    }

    public Journal createJournal(Journal journal) {
        journal.setJournalDate(LocalDateTime.now());
        return repository.save(journal);
    }

    public List<Journal> getAllJournals() {
        return repository.findAll();
    }

    public Journal getJournalById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteJournal(Long id) {
        repository.deleteById(id);
    }
}