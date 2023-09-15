package com.emotionbank.business.domain.transaction.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.global.error.exception.BusinessException;

class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private AccountRepository accountRepository;

	@BeforeEach
	public void beforeEach(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("입금을 성공적으로 처리한다")
	void depositSuccessfully() {
		TransactionDto transactionDto = TransactionDto.builder()
			.amount(100L)
			.sender("777-7777")
			.receiver("999-9999")
			.build();

		Account senderAccount = Account.builder()
			.accountNumber("777-7777")
			.balance(1000L)
			.build();

		Account receiverAccount = Account.builder()
			.accountNumber("999-9999")
			.balance(2000L)
			.build();

		when(accountRepository.findByAccountNumber("777-7777"))
			.thenReturn(Optional.of(senderAccount));
		when(accountRepository.findByAccountNumber("999-9999"))
			.thenReturn(Optional.of(receiverAccount));

		// When
		TransactionDto resultDto = transactionService.deposit(transactionDto);

		// Then
		assertThat(resultDto).isNotNull();
		assertThat(resultDto.getBalance()).isEqualTo(2100L);
	}
}