package com.example.financialtransactionsystemimpl.service.impl;

import com.example.financialtransactionsystemimpl.exception.ClientException;
import com.example.financialtransactionsystemimpl.model.Client;
import com.example.financialtransactionsystemimpl.model.Phone;
import com.example.financialtransactionsystemimpl.repository.PhoneRepository;
import com.example.financialtransactionsystemimpl.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public Phone createPhone(Client client, Map<String, Object> requestMap) {
        String newPhone = String.valueOf(requestMap.get("phone"));

        validate(newPhone);

        return phoneRepository.save(Phone.builder().phone(newPhone).client(client).build());
    }

    public void updatePhone(Client client, Long phoneId, Map<String, Object> requestMap) {
        String newPhone = String.valueOf(requestMap.get("phone"));

        validate(newPhone);

        Phone phoneFromDb = findById(phoneId);

        if (!phoneFromDb.getClient().equals(client)){
            throw new RuntimeException("Разрешено изменение только личных данных");
        }

        phoneFromDb.setPhone(newPhone);
        phoneRepository.save(phoneFromDb);
    }

    public void deletePhone(Client client,Long phoneId) {
        Phone phoneForDelete = findById(phoneId);

        if (!phoneForDelete.getClient().equals(client)){
            throw new RuntimeException("Разрешено удаление только личных данных");
        }

        phoneRepository.delete(phoneForDelete);
    }

    public Phone findById(Long id) {
        return phoneRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Не найден phone id=" + id);
                });
    }

    public void validate(String phone) {
        if (phoneRepository.findByPhone(phone).isPresent()) {
            throw ClientException.phoneAlreadyExists();
        }
    }
}
