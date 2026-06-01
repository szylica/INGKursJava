package com.example.walltermastering.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("OUTCOME")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Outcome extends Transaction {

    private Category category;
}
