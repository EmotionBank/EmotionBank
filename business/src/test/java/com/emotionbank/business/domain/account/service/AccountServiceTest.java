package com.emotionbank.business.domain.account.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;


	@Test
	@DisplayName("계좌생성")
	void createAccount() {
		// Given
		AccountService accountService = new AccounServiceImpl(accountRepository, userRepository);
		assertNotNull(accountService);
		User user = userRepository.save(User.builder()
			.nickname("TEST NAME")
			.build());
		String accountName = "테스트용계좌";
		// When
		AccountDto accountDto = accountService.createAccount(user.getUserId(), accountName);

		// Then
		assertEquals(accountDto.getAccountId(), 1L);
		assertEquals(accountDto.getAccountName(), accountName);
		assertEquals(accountDto.getUserId(), user.getUserId());
		assertEquals(accountDto.getAccountNumber().length(),"yyMM-ddHH-mmssSSS".length());
		assertEquals(accountDto.getBalance(), 0L);

		// 확인용 출력
		// System.out.println(accountDto.getAccountId());
		// System.out.println(accountDto.getAccountName());
		// System.out.println(accountDto.getAccountNumber());
		// System.out.println(accountDto.getBalance());
		// System.out.println(accountDto.getCreatedTime());
	}

	@Test
	@DisplayName("잔액조회")
	void getAccountBalance() {
		AccountService accountService = new AccounServiceImpl(accountRepository, userRepository);
		assertNotNull(accountService);
		Account account = Account.builder()
			.accountName("테스트용 계좌")
			.user(userRepository.save(User.builder()
				.nickname("TEST NAME")
				.build()))
			.accountNumber("123-4567")
			.balance(10000L)
			.build();
		// When
		accountRepository.save(account);

		// Then
		AccountDto accountDto = accountService.getAccountBalance(account.getAccountNumber());
		assertEquals(accountDto.getAccountId(), 1L);
		assertEquals(accountDto.getBalance(), 10000L);
	}

	@Test
	@DisplayName("계좌명 변경")
	@Transactional
	void changeAccountName() {
		AccountService accountService = new AccounServiceImpl(accountRepository, userRepository);
		assertNotNull(accountService);
		Account account = Account.builder()
			.accountName("테스트용 계좌")
			.user(userRepository.save(User.builder()
				.nickname("TEST NAME")
				.build()))
			.accountNumber("123-4567")
			.balance(10000L)
			.build();
		accountRepository.save(account);

		// When
		String accountName = "지은이 계좌";
		AccountDto accountDto = accountService.updateAccountName("123-4567", accountName);

		// Then
		Account findAccount = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		System.out.println(findAccount.getAccountName());
		assertEquals(findAccount.getAccountName(), accountName);
	}
}