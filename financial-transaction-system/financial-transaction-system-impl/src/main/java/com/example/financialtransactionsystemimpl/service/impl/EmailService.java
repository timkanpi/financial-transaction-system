package com.example.financialtransactionsystemimpl.service.impl;

import com.example.financialtransactionsystemimpl.exception.ClientException;
import com.example.financialtransactionsystemimpl.model.Email;
import com.example.financialtransactionsystemimpl.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository repository;

    @Transactional
    public void updateEmail(Long emailId, Map<String, Object> requestMap) {
        String newEmail = String.valueOf(requestMap.get("email"));

        validate(newEmail);

        Email email = findById(emailId);

        email.setEmail(newEmail);
        repository.save(email);
    }

    @Transactional
    public void deleteEmail(Long emailId) {
        Email emailForDelete = findById(emailId);
        if (repository.findAllByClient_Id(emailForDelete.getClient().getId()).size() == 1) {
            throw new RuntimeException("Нельзя удалить последний email клиента");
        }

        repository.delete(emailForDelete);
    }

    public Email findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Не найден email id=" + id);
                });
    }

    public void validate(String email) {
        if (email != null && repository.findByEmail(email).isPresent()) {
            throw ClientException.emailAlreadyExists();
        }
    }
}
