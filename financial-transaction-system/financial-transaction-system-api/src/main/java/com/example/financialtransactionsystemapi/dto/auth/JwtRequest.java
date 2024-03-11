package com.example.financialtransactionsystemapi.dto.auth;

import lombok.Data;

@Data
public class JwtRequest {

    private String login;
    private String password;
}
