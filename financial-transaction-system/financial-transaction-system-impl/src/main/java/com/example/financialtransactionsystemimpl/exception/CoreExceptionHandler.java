package com.example.financialtransactionsystemimpl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ExceptionMessageDto> handleClientException(ClientException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessageDto(exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionMessageDto> handleClientException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessageDto(exception.getMessage()));
    }
}
