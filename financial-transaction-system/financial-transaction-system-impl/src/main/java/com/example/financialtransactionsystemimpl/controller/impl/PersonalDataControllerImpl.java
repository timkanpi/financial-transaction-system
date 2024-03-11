package com.example.financialtransactionsystemimpl.controller.impl;

import com.example.financialtransactionsystemapi.controller.PersonalDataController;
import com.example.financialtransactionsystemimpl.model.Client;
import com.example.financialtransactionsystemimpl.service.ClientService;
import com.example.financialtransactionsystemimpl.service.impl.EmailService;
import com.example.financialtransactionsystemimpl.service.impl.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PersonalDataControllerImpl implements PersonalDataController {

    private final ClientService clientService;
    private final PhoneService phoneService;
    private final EmailService emailService;

    @Override
    public void createPhone(Map<String, Object> requestMap) {
        phoneService.createPhone(clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()), requestMap);
    }

    @Override
    public void updatePhone(Long phoneId, Map<String, Object> requestMap) {
        phoneService.updatePhone(clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()), phoneId, requestMap);
    }

    @Override
    public void deletePhone(Long phoneId) {
        phoneService.deletePhone(clientService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName()), phoneId);
    }

    @Override
    public void updateEmail(Long emailId, Map<String, Object> requestMap) {
        emailService.updateEmail(emailId, requestMap);
    }

    @Override
    public void deleteEmail(Long emailId) {
        emailService.deleteEmail(emailId);
    }
}
