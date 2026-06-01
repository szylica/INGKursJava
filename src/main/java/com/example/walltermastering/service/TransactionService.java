package com.example.walltermastering.service;

import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    BigDecimal calculateActualBalance();
    List<Transaction> getAllTransactions();
    List<Transaction> getAllOutcomes();
    List<Transaction> getAllIncomes();

}
