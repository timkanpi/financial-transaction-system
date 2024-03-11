package com.example.financialtransactionsystemimpl.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExceptionMessageDto {

    private String message;
}
