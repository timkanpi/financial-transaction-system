package com.example.financialtransactionsystemapi.dto;

import lombok.Data;

@Data
public class ClientCreateDto {

    private String login;
    private String password;

    private String fio;
    private String birthDate;

    private String phone;
    private String email;

    private Double startBalance;
}
