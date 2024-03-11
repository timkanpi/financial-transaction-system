package com.example.financialtransactionsystemimpl.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "bankAccount")
    private Client client;

    @Min(value = 0)
    @Digits(integer = 12, fraction = 2)
    private BigDecimal balance;

    @Min(value = 0)
    private BigDecimal initialBalance;
}
