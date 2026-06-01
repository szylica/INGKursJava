package com.example.walltermastering.repository;

import com.example.walltermastering.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Override
    void deleteById(Long aLong);
}
