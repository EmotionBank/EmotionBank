package com.emotionbank.business.domain.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emotionbank.business.domain.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByAccountNumber(String accountNumber);
}
