package com.example.financialtransactionsystemimpl.schedule;

import com.example.financialtransactionsystemimpl.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankAccountScheduler {

    private final BankAccountService bankAccountService;

    @Scheduled(initialDelay = 1000L, fixedDelay = 60000L)
    public void incrementBankAccount() {
        bankAccountService.updateAccounts();
    }
}
