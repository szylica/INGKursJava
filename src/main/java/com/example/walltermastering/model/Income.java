package com.example.walltermastering.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("INCOME")
@AllArgsConstructor
@NoArgsConstructor
public class Income extends Transaction {

    private String source;

}
