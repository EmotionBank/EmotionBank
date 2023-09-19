package com.emotionbank.business.domain.transaction.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("SELECT t FROM Transaction t WHERE (t.sender = :account OR t.receiver = :account) AND date(t.transactionTime) = :date")
	List<Transaction> searchByAccountAndDate(Account account, Date date);
}