package com.example.walltermastering.service.impl;

import com.example.walltermastering.model.Income;
import com.example.walltermastering.model.Outcome;
import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.repository.TransactionRepository;
import com.example.walltermastering.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {

        if(transaction == null || transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("Transaction must have a positive amount");
        }


        return transactionRepository.save(transaction);
    }

    @Override
    public BigDecimal calculateActualBalance() {

        return transactionRepository
                .findAll()
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllOutcomes() {
        return getAllTransactions()
                .stream()
                .filter(transaction -> transaction instanceof Outcome)
                .toList();
    }

    @Override
    public List<Transaction> getAllIncomes() {
        return getAllTransactions()
                .stream()
                .filter(transaction -> transaction instanceof Income)
                .toList();
    }


}
