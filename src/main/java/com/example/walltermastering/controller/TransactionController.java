package com.example.walltermastering.controller;

import com.example.walltermastering.model.Category;
import com.example.walltermastering.model.Income;
import com.example.walltermastering.model.Outcome;
import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Outcome>> getAllOutcomes() {
        List<Outcome> transactions = transactionService.getAllOutcomes();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/api/get-incomes")
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> transactions = transactionService.getAllIncomes();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/api/get-outcome-by-cat")
    public ResponseEntity<List<Outcome>> getOutcomesByCategory(@PathVariable Category category){
        List<Outcome> transactions = transactionService.getTransactionsByCategory(category);
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("/api/delete-by-id")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id){
        transactionService.deleteByID(id);
        return ResponseEntity.ok().build();
    }

}
