package com.example.financialtransactionsystemimpl.service;

import com.example.financialtransactionsystemapi.dto.TransferRq;

public interface BankAccountService {

    void transaction(Long fromAccount, TransferRq transferRq);

    void updateAccounts();
}
