package com.example.walltermastering.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OUTCOME")
public class Outcome extends Transaction {

    private Category category;
}
