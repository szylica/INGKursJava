package com.example.walltermastering.controller;

import com.example.walltermastering.model.Income;
import com.example.walltermastering.model.Outcome;
import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/api/add-income")
    public ResponseEntity<Transaction> addIncome(@RequestBody Income income) {

        Transaction createdTransaction = transactionService.saveTransaction(income);

        return ResponseEntity.ok(createdTransaction);
    }

    @PostMapping("/api/add-outcome")
    public ResponseEntity<Transaction> addOutcome(@RequestBody Outcome outcome) {

        Transaction createdTransaction = (Outcome) transactionService.saveTransaction(outcome);

        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/api/get-balance")
    public ResponseEntity<BigDecimal> getActualBalance() {
        BigDecimal actualBalance = transactionService.calculateActualBalance();
        return ResponseEntity.ok(actualBalance);
    }

    @GetMapping("/api/get-transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/api/get-outcomes")
    public ResponseEntity<List<Transaction>> getAllOutcomes() {
        List<Transaction> transactions = transactionService.getAllOutcomes();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/api/get-incomes")
    public ResponseEntity<List<Transaction>> getAllIncomes() {
        List<Transaction> transactions = transactionService.getAllIncomes();
        return ResponseEntity.ok(transactions);
    }
}
