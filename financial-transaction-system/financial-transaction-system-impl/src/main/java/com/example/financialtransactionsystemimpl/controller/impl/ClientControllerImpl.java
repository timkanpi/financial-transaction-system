package com.example.financialtransactionsystemimpl.controller.impl;

import com.example.financialtransactionsystemapi.controller.ClientController;
import com.example.financialtransactionsystemapi.dto.ClientCreateDto;
import com.example.financialtransactionsystemapi.dto.ClientDto;
import com.example.financialtransactionsystemimpl.mapper.ClientMapper;
import com.example.financialtransactionsystemimpl.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @Override
    public ClientDto createClient(ClientCreateDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @Override
    public ResponseEntity<List<ClientDto>> getListOfClients(int pageNumber, String sortBy, String order, String birthDate, String phone, String fio, String email) {
        return ResponseEntity.ok(clientService.getList(pageNumber, sortBy, order, birthDate, phone, fio, email));
    }
}
