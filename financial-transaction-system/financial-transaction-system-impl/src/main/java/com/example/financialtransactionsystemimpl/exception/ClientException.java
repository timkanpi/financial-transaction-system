package com.example.financialtransactionsystemimpl.exception;

public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }

    public static ClientException emailAlreadyExists() {
        return new ClientException("Клиент с таким email уже существует");
    }

    public static ClientException phoneAlreadyExists() {
        return new ClientException("Клиент с таким phone уже существует");
    }

    public static ClientException loginAlreadyExists() {
        return new ClientException("Клиент с таким login уже существует");
    }

    public static ClientException emailAndPhoneIsEmpty() {
        return new ClientException("Должен быть указан email или phone");
    }
}
