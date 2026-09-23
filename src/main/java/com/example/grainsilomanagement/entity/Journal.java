package com.example.grainsilomanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String journalName;
    private String journalType;
    private String reference;
    private Double amount;
    private LocalDateTime journalDate;

    public Journal() {
    }

    public Long getId() {
        return id;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(LocalDateTime journalDate) {
        this.journalDate = journalDate;
    }
}