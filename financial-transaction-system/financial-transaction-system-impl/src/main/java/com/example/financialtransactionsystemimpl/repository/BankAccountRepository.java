package com.example.financialtransactionsystemimpl.repository;

import com.example.financialtransactionsystemimpl.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


}
