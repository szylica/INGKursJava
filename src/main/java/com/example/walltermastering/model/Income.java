package com.example.walltermastering.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INCOME")
public class Income extends Transaction {

    private String source;

}
