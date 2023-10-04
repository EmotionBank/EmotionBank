package com.emotionbank.business.domain.transaction.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("SELECT t FROM Transaction t WHERE ((t.sender = :account and t.receiver = :account) or (t.sender = :account and t.transactionType = 'WITHDRAWL') or (t.receiver =:account and t.transactionType = 'DEPOSIT')) AND DATE(t.transactionTime) BETWEEN :startDate AND :endDate")
	List<Transaction> searchTransactionByAccountAndDate(Account account,
		Date startDate,
		Date endDate
	);

	// @Query("SELECT t FROM Transaction t WHERE (t.sender = :account OR t.receiver = :account) AND t.category.visibility = :visibility AND DATE(t.transactionTime) BETWEEN :startDate AND :endDate")
	// List<Transaction> findByAccountAndDateAndVisibility(Account account,
	// 	Date startDate,
	// 	Date endDate,
	// 	Visibility visibility
	// );

	@Query("SELECT t FROM Transaction t WHERE ((t.sender = :account and t.receiver = :account) or (t.sender = :account and t.transactionType = 'WITHDRAWL') or (t.receiver =:account and t.transactionType = 'DEPOSIT')) AND t.category.visibility = :visibility AND DATE(t.transactionTime) BETWEEN :startDate AND :endDate")
	List<Transaction> findByAccountAndDateAndVisibility(Account account,
		Date startDate,
		Date endDate,
		Visibility visibility
	);

	Optional<Transaction> findByTransactionId(Long transactionId);

	List<Transaction> findByCategory(Category category);

	@Query("SELECT t FROM Transaction t WHERE (t.sender = t.receiver) AND (t.sender <> :account) AND (t.receiver <> :account)")
	List<Transaction> findFeed(@Param("account") Account account, Pageable pageable);
}