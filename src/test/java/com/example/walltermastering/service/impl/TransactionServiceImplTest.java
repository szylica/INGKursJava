package com.example.walltermastering.service.impl;

import com.example.walltermastering.model.Category;
import com.example.walltermastering.model.Income;
import com.example.walltermastering.model.Outcome;
import com.example.walltermastering.model.Transaction;
import com.example.walltermastering.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    @DisplayName("Should save transaction when amount is positive")
    void shouldSaveTransactionWhenAmountIsPositive() {
        // Given
        Outcome outcome = new Outcome();
        outcome.setAmount(new BigDecimal("150.00"));
        outcome.setDescription("Zakupy");

        when(transactionRepository.save(any(Transaction.class))).thenReturn(outcome);

        // When
        Transaction savedTransaction = transactionService.saveTransaction(outcome);

        // Then
        assertNotNull(savedTransaction);
        assertEquals(new BigDecimal("150.00"), savedTransaction.getAmount());
        verify(transactionRepository, times(1)).save(outcome);
    }

    @Test
    @DisplayName("Should throw exception when saving null transaction")
    void shouldThrowExceptionWhenSavingNullTransaction() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.saveTransaction(null);
        });

        assertEquals("Transaction must have a positive amount", exception.getMessage());
        verifyNoInteractions(transactionRepository);
    }

    @Test
    @DisplayName("Should throw exception when transaction amount is zero or negative")
    void shouldThrowExceptionWhenAmountIsZeroOrNegative() {
        // Given
        Outcome zeroOutcome = new Outcome();
        zeroOutcome.setAmount(BigDecimal.ZERO);

        Outcome negativeOutcome = new Outcome();
        negativeOutcome.setAmount(new BigDecimal("-10.00"));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> transactionService.saveTransaction(zeroOutcome));
        assertThrows(IllegalArgumentException.class, () -> transactionService.saveTransaction(negativeOutcome));

        verifyNoInteractions(transactionRepository);
    }

    @Test
    @DisplayName("Should calculate actual balance correctly by summing all transactions")
    void shouldCalculateActualBalance() {
        // Given
        Outcome t1 = new Outcome();
        t1.setAmount(new BigDecimal("100.00"));

        Income t2 = Mockito.mock(Income.class);
        when(t2.getAmount()).thenReturn(new BigDecimal("50.50"));


        when(transactionRepository.findAll()).thenReturn(List.of(t1, t2));

        // When
        BigDecimal totalBalance = transactionService.calculateActualBalance();

        // Then
        assertEquals(new BigDecimal("-49.50"), totalBalance);
    }

    @Test
    @DisplayName("Should return only Outcome transactions from getAllOutcomes")
    void shouldReturnOnlyOutcomes() {
        // Given
        Outcome outcome = new Outcome();
        Income income = Mockito.mock(Income.class);

        when(transactionRepository.findAll()).thenReturn(List.of(outcome, income));

        // When
        List<Outcome> outcomes = transactionService.getAllOutcomes();

        // Then
        assertEquals(1, outcomes.size());
        assertTrue(outcomes.contains(outcome));
        assertFalse(outcomes.contains(income));
    }

    @Test
    @DisplayName("Should filter outcomes by specific category")
    void shouldFilterOutcomesByCategory() {
        // Given
        Category foodCategory = Mockito.mock(Category.class);
        Category workCategory = Mockito.mock(Category.class);

        Outcome foodOutcome = new Outcome();
        foodOutcome.setCategory(foodCategory);

        Outcome workOutcome = new Outcome();
        workOutcome.setCategory(workCategory);

        when(transactionRepository.findAll()).thenReturn(List.of(foodOutcome, workOutcome));

        // When
        List<Outcome> result = transactionService.getTransactionsByCategory(foodCategory);

        // Then
        assertEquals(1, result.size());
        assertEquals(foodCategory, result.get(0).getCategory());
    }

    @Test
    @DisplayName("Should call repository delete method when deleting by ID")
    void shouldCallDeleteInRepository() {
        // Given
        Long idToDelete = 1L;

        // When
        transactionService.deleteByID(idToDelete);

        // Then
        verify(transactionRepository, times(1)).deleteById(idToDelete);
    }
}