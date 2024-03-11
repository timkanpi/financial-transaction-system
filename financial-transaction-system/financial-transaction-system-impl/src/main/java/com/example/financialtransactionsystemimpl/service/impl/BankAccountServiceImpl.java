package com.example.financialtransactionsystemimpl.service.impl;

import com.example.financialtransactionsystemapi.dto.TransferRq;
import com.example.financialtransactionsystemimpl.model.BankAccount;
import com.example.financialtransactionsystemimpl.repository.BankAccountRepository;
import com.example.financialtransactionsystemimpl.service.BankAccountService;
import com.example.financialtransactionsystemimpl.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final ClientService clientService;

    @Override
    @Transactional
    public void transaction(Long fromAccountId, TransferRq transferRq) {
        BankAccount fromAccount = findById(clientService.getById(fromAccountId).getId());
        BankAccount toAccount = findById(clientService.getByPhone(transferRq.getReceiverClientPhone()).getId());

        fromAccount.setBalance(fromAccount.getBalance().subtract(BigDecimal.valueOf(transferRq.getAmount())));
        toAccount.setBalance(toAccount.getBalance().add(BigDecimal.valueOf(transferRq.getAmount())));

        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);
    }

    @Override
    public void updateAccounts() {
        List<BankAccount> collect = bankAccountRepository.findAll()
                .stream()
                .peek(bankAccount -> {
            BigDecimal maxValue = bankAccount.getInitialBalance().multiply(BigDecimal.valueOf(2.07)).setScale(2, RoundingMode.HALF_UP);
            BigDecimal newValue = bankAccount.getBalance().multiply(BigDecimal.valueOf(1.05)).setScale(2, RoundingMode.HALF_UP);

            if (newValue.compareTo(maxValue) < 0) {
                bankAccount.setBalance(newValue);
            } else if (newValue.compareTo(maxValue) > 0) {
                bankAccount.setBalance(maxValue);
            }
        }).collect(Collectors.toList());

        bankAccountRepository.saveAll(collect);
    }

    public BankAccount findById(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Не найден банковский счет с id=" + id));
    }
}
