package com.example.walltermastering.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("OUTCOME")
@AllArgsConstructor
@NoArgsConstructor
public class Outcome extends Transaction {

    private Category category;
}
