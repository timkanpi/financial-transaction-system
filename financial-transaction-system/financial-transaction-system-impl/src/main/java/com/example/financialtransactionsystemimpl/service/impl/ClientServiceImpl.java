package com.example.financialtransactionsystemimpl.service.impl;

import com.example.financialtransactionsystemapi.dto.ClientCreateDto;
import com.example.financialtransactionsystemapi.dto.ClientDto;
import com.example.financialtransactionsystemimpl.exception.ClientException;
import com.example.financialtransactionsystemimpl.mapper.ClientMapper;
import com.example.financialtransactionsystemimpl.model.Client;
import com.example.financialtransactionsystemimpl.repository.ClientRepository;
import com.example.financialtransactionsystemimpl.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Value("${page.size}")
    private int pageSize;

    private final ClientRepository clientRepository;
    private final PhoneService phoneService;
    private final EmailService emailService;

    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto createClient(ClientCreateDto clientDto) {
        if (ObjectUtils.isEmpty(clientDto.getEmail()) && ObjectUtils.isEmpty(clientDto.getPhone())) {
            throw ClientException.emailAndPhoneIsEmpty();
        }

        validateUniqFields(clientDto);

        Client savedClient = clientRepository.save(clientMapper.map(clientDto));

        return clientMapper.map(savedClient);
    }

    @Override
    public List<ClientDto> getList(int pageNumber, String sortBy, String order, String birthDate, String phone, String fio, String email) {
        Sort sorting = Sort.by(sortBy);
        Pageable paging = PageRequest.of(pageNumber, pageSize, order.equals("asc") ? sorting.ascending() : sorting.descending());

        Page<Client> page;

        if (birthDate != null)
            page = clientRepository.findAllByBirthDateAfter(birthDate, paging);

        else if (phone != null) {
            page = clientRepository.findAllByPhone_Phone(phone, paging);
        } else if (fio != null) {
            page = clientRepository.findAllByFioLikeIgnoreCase(fio, paging);
        } else if (email != null) {
            page = clientRepository.findAllByEmail_Email(email, paging);
        } else {
            page = clientRepository.findAll(paging);
        }

        return page.stream().map(clientMapper::map).collect(Collectors.toList());
    }

    private void validateUniqFields(ClientCreateDto clientDto) {
        if (clientRepository.findByLogin(clientDto.getLogin()).isPresent()) {
            throw ClientException.loginAlreadyExists();
        }

        phoneService.validate(clientDto.getPhone());
        emailService.validate(clientDto.getEmail());
    }

    public Client getByLogin(String login) {
        return clientRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Не найден client c login=" + login));
    }


    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Не найден client c id=" + id));
    }

    public Client getByPhone(String phone) {
        return clientRepository.findByPhone_Phone(phone).orElseThrow(() -> new RuntimeException("Не найден client c phone=" + phone));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByLogin(username);
    }
}