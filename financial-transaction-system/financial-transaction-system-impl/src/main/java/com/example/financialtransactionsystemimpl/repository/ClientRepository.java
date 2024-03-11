package com.example.financialtransactionsystemimpl.repository;

import com.example.financialtransactionsystemimpl.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByLogin(String login);
    Optional<Client> findByPhone_Phone(String phone);


    Page<Client> findAllByBirthDateAfter(String birthDate, Pageable pageable);

    Page<Client> findAllByPhone_Phone(String phone, Pageable pageable);

    Page<Client> findAllByEmail_Email(String email, Pageable pageable);

    Page<Client> findAllByFioLikeIgnoreCase(String fio, Pageable pageable);


}
