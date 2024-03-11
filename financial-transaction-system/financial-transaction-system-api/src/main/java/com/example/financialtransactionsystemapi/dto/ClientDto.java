package com.example.financialtransactionsystemapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private String fio;
    private String birthDate;

    private List<String> email;
    private List<String> phone;
}
