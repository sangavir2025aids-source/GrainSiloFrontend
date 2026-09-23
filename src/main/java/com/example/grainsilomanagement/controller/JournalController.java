package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.Journal;
import com.example.grainsilomanagement.service.JournalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
@CrossOrigin
public class JournalController {

    private final JournalService service;

    public JournalController(JournalService service) {
        this.service = service;
    }

    @PostMapping
    public Journal createJournal(@RequestBody Journal journal) {
        return service.createJournal(journal);
    }

    @GetMapping
    public List<Journal> getAllJournals() {
        return service.getAllJournals();
    }

    @GetMapping("/{id}")
    public Journal getJournalById(@PathVariable Long id) {
        return service.getJournalById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJournal(@PathVariable Long id) {
        service.deleteJournal(id);
        return "Journal deleted successfully";
    }
}