package com.example.financialtransactionsystemimpl.repository;

import com.example.financialtransactionsystemimpl.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    Optional<Email> findByEmail(String email);

    Optional<Email> findByClientIdAndId(Long id, Long emailId);

    List<Email> findAllByClient_Id(Long clientId);
}
