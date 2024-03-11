package com.example.financialtransactionsystemimpl.service;

import com.example.financialtransactionsystemapi.dto.auth.JwtAuthentication;
import com.example.financialtransactionsystemapi.dto.auth.JwtRequest;
import com.example.financialtransactionsystemapi.dto.auth.JwtResponse;
import com.example.financialtransactionsystemimpl.model.Client;
import com.example.financialtransactionsystemimpl.provider.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientService clientService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final Client client = clientService.getByLogin(authRequest.getLogin());
        if (client.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(client);
            final String refreshToken = jwtProvider.generateRefreshToken(client);
            refreshStorage.put(client.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Неправильный пароль");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Client client = clientService.getByLogin(login);
                final String accessToken = jwtProvider.generateAccessToken(client);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Client client = clientService.getByLogin(login);
                final String accessToken = jwtProvider.generateAccessToken(client);
                final String newRefreshToken = jwtProvider.generateRefreshToken(client);
                refreshStorage.put(client.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new RuntimeException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
