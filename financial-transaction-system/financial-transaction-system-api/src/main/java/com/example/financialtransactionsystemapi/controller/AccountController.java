package com.example.financialtransactionsystemapi.controller;

import com.example.financialtransactionsystemapi.dto.TransferRq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "BankAccount")
@RequestMapping(path = "/account")
public interface AccountController {

    @Operation(summary = "Перевод ден. средств со счета авторизованного клиента на счет другого")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok")})
    @PostMapping(path = "/transfer")
    void createTransaction(@Valid @RequestBody TransferRq transferRq);
}
