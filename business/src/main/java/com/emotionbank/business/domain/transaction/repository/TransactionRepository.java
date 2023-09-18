package com.emotionbank.business.domain.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emotionbank.business.domain.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}