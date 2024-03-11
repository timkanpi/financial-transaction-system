package com.example.financialtransactionsystemimpl.controller.impl;

import com.example.financialtransactionsystemapi.controller.AuthController;
import com.example.financialtransactionsystemapi.dto.auth.JwtRequest;
import com.example.financialtransactionsystemapi.dto.auth.JwtResponse;
import com.example.financialtransactionsystemapi.dto.auth.RefreshJwtRequest;
import com.example.financialtransactionsystemimpl.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ResponseEntity<JwtResponse> login(JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<JwtResponse> getNewAccessToken(RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<JwtResponse> getNewRefreshToken(RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
