package com.example.financialtransactionsystemimpl.service;

import com.example.financialtransactionsystemapi.dto.ClientCreateDto;
import com.example.financialtransactionsystemapi.dto.ClientDto;
import com.example.financialtransactionsystemimpl.model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {

    ClientDto createClient(ClientCreateDto clientDto);

    List<ClientDto> getList(int pageNumber, String sortBy, String order, String birthDate, String phone, String fio, String email);

    Client getByLogin(String login);

    Client getById(Long id);

    Client getByPhone(String phone);

}
