package com.example.financialtransactionsystemapi.controller;

import com.example.financialtransactionsystemapi.dto.ClientCreateDto;
import com.example.financialtransactionsystemapi.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/client")
public interface ClientController {

    @Operation(summary = "Создание клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created", content = {@Content(mediaType = "application/json")})})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClientDto createClient(@Valid @RequestBody ClientCreateDto clientDto);

    @Operation(summary = "поиск клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json")})})
    @GetMapping(path = "/list")
    ResponseEntity<List<ClientDto>> getListOfClients(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "fio") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String order,
                                                     @RequestParam(name = "birthDate", required = false) String birthDate,
                                                     @RequestParam(name = "phone", required = false) String phone,
                                                     @RequestParam(name = "fio", required = false) String fio,
                                                     @RequestParam(name = "email", required = false) String email);
}
