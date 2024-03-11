package com.example.financialtransactionsystemimpl.repository;

import com.example.financialtransactionsystemimpl.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findByPhone(String phone);
}
