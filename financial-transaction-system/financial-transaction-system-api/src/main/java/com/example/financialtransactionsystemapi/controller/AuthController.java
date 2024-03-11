package com.example.financialtransactionsystemapi.controller;

import com.example.financialtransactionsystemapi.dto.auth.JwtRequest;
import com.example.financialtransactionsystemapi.dto.auth.JwtResponse;
import com.example.financialtransactionsystemapi.dto.auth.RefreshJwtRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "authorization")
@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping(path = "/login")
    ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest);

    @PostMapping(path = "/token")
    ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request);

    @PostMapping(path = "/refresh")
    ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request);
}
