package com.example.walltermastering.controller;

import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {

        Transaction createdTransaction = transactionService.saveTransaction(transaction);

        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping
    public ResponseEntity<BigDecimal> getActualBalance() {
        BigDecimal actualBalance = transactionService.calculateActualBalance();
        return ResponseEntity.ok(actualBalance);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllOutcomes() {
        List<Transaction> transactions = transactionService.getAllOutcomes();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllIncomes() {
        List<Transaction> transactions = transactionService.getAllIncomes();
        return ResponseEntity.ok(transactions);
    }
}
