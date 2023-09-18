package com.emotionbank.business.domain.transaction.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.domain.user.entity.Category;
import com.emotionbank.business.domain.user.repository.CategoryRepository;

class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("입금을 성공적으로 처리한다")
	void depositSuccessfully() {
		TransactionDto transactionDto = TransactionDto.builder()
			.transactionType(TransactionType.DEPOSIT)
			.amount(1000L)
			.balance(20000L)
			.categoryId(1L)
			.sender("777-7777")
			.receiver("777-7777")
			.build();

		Account account = Account.builder()
			.accountNumber("777-7777")
			.balance(20000L)
			.build();

		Category category = Category.builder()
			.categoryId(1L)
			.categoryName("회사")
			.build();

		when(accountRepository.findByAccountNumber("777-7777"))
			.thenReturn(Optional.of(account));
		when(categoryRepository.findByCategoryId(1L))
			.thenReturn(Optional.of(category));

		// When
		TransactionDto resultDto = transactionService.updateBalance(transactionDto);

		// Then
		assertThat(resultDto).isNotNull();
		assertThat(resultDto.getBalance()).isEqualTo(21000L);
	}
}