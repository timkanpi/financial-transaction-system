package com.example.financialtransactionsystemimpl.controller.impl;

import com.example.financialtransactionsystemapi.controller.AccountController;
import com.example.financialtransactionsystemapi.dto.TransferRq;
import com.example.financialtransactionsystemimpl.model.Client;
import com.example.financialtransactionsystemimpl.service.BankAccountService;
import com.example.financialtransactionsystemimpl.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final BankAccountService bankAccountService;
    private final ClientService clientService;

    @Override
    public void createTransaction(TransferRq transferRq) {
        Client client = clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        bankAccountService.transaction(client.getId(), transferRq);
    }
}
