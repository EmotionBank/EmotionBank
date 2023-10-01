package com.emotionbank.business.domain.transaction.service;

import java.util.List;

import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.dto.TransactionSearchDto;
import com.emotionbank.business.domain.transaction.dto.TransactionTransferDto;
import com.emotionbank.business.domain.transaction.dto.TransactionUpdateDto;

public interface TransactionService {
	TransactionDto updateBalance(TransactionDto transactionDto, Long userId);

	List<TransactionDto> getTransactions(TransactionSearchDto transactionSearchDto);

	TransactionDto getTransactionDetail(Long transactionId, Long userId);

	void updateTransaction(TransactionUpdateDto transactionUpdateDto);

	TransactionDto transfer(TransactionTransferDto transactionTransferDto);
}