package com.emotionbank.business.domain.transaction.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("SELECT t FROM Transaction t WHERE (t.sender = :account OR t.receiver = :account) AND DATE(t.transactionTime) BETWEEN :startDate AND :endDate")
	List<Transaction> searchTransactionByAccountAndDate(Account account,
		Date startDate,
		Date endDate
	);

	@Query("SELECT t FROM Transaction t WHERE (t.sender = :account OR t.receiver = :account) AND t.visibility = :visibility AND DATE(t.transactionTime) BETWEEN :startDate AND :endDate")
	List<Transaction> searchTransactionByAccountAndDateAndVisibility(Account account,
		Date startDate,
		Date endDate,
		Visibility visibility
	);

	Optional<Transaction> findByTransactionId(Long transactionId);
}