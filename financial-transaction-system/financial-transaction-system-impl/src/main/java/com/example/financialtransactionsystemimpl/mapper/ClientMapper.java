package com.example.financialtransactionsystemimpl.mapper;

import com.example.financialtransactionsystemapi.dto.ClientCreateDto;
import com.example.financialtransactionsystemapi.dto.ClientDto;
import com.example.financialtransactionsystemimpl.model.BankAccount;
import com.example.financialtransactionsystemimpl.model.Client;
import com.example.financialtransactionsystemimpl.model.Email;
import com.example.financialtransactionsystemimpl.model.Phone;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public Client map(ClientCreateDto clientDto) {
        return Client.builder()
                .login(clientDto.getLogin())
                .password(clientDto.getPassword())
                .email(List.of(Email.builder()
                        .email(clientDto.getEmail())
                        .build()))
                .phone(List.of(Phone.builder()
                        .phone(clientDto.getPhone())
                        .build()))
                .fio(clientDto.getFio())
                .birthDate(clientDto.getBirthDate())
                .bankAccount(BankAccount.builder()
                        .balance(BigDecimal.valueOf(clientDto.getStartBalance()))
                        .initialBalance(BigDecimal.valueOf(clientDto.getStartBalance()))
                        .build())
                .build();
    }

    public ClientDto map(Client client) {
        return ClientDto.builder()
                .fio(client.getFio())
                .email(client.getEmail().stream().map(Email::getEmail).collect(Collectors.toList()))
                .phone(client.getPhone().stream().map(Phone::getPhone).collect(Collectors.toList()))
                .birthDate(client.getBirthDate())
                .build();
    }
}
