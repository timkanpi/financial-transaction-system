package com.example.financialtransactionsystemapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class TransferRq {

    private String receiverClientPhone;

    @DecimalMin(value = "0.01", inclusive = false)
    @Digits(integer = 12, fraction = 2)
    private Double amount;
}
