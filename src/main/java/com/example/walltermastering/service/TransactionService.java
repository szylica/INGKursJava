package com.example.walltermastering.service;

import com.example.walltermastering.model.Category;
import com.example.walltermastering.model.Income;
import com.example.walltermastering.model.Outcome;
import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.repository.TransactionRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    BigDecimal calculateActualBalance();
    List<Transaction> getAllTransactions();
    List<Outcome> getAllOutcomes();
    List<Income> getAllIncomes();
    List<Outcome> getTransactionsByCategory(Category category);
    void deleteByID(Long id);
}
