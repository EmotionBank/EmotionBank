package com.emotionbank.business.domain.transaction.service;

import java.util.List;

import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.dto.TransactionSearchDto;
import com.emotionbank.business.domain.transaction.dto.TransactionTransferDto;

public interface TransactionService {
	TransactionDto updateBalance(TransactionDto transactionDto);

	List<TransactionDto> getTransactions(TransactionSearchDto transactionSearchDto);

	TransactionDto getTransactionDetail(Long transactionId);

	long transfer(TransactionTransferDto transactionTransferDto);
}