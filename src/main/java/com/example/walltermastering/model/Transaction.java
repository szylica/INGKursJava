package com.example.walltermastering.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Transaction {
    @Id
    private Long id;
    private BigDecimal amount;
    private String description;
    private ZonedDateTime date;

    public Transaction(BigDecimal amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Transaction() {}


}
