package com.example.financialtransactionsystemimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinancialTransactionSystemImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialTransactionSystemImplApplication.class, args);
    }

}
