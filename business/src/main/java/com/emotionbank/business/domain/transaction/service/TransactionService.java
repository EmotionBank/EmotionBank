package com.emotionbank.business.domain.transaction.service;

import com.emotionbank.business.domain.transaction.dto.TransactionDto;

public interface TransactionService {
	// 입금
	TransactionDto updateBalance(TransactionDto transactionDto);

}