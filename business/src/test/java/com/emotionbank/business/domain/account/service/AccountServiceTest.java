package com.emotionbank.business.domain.account.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.constant.SocialType;
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
	@Transactional
	@DisplayName("계좌생성")
	void createAccount() {
		// Given
		AccountService accountService = new AccountServiceImpl(accountRepository, userRepository);
		assertNotNull(accountService);

		User user = userRepository.save(User.builder()
			.nickname("TEST NAME")
			.birthday(LocalDate.now())
			.role(Role.USER)
			.socialId("123123")
			.socialType(SocialType.KAKAO)
			.build());
		String accountName = "테스트용계좌";

		// When
		AccountDto accountDto = accountService.createAccount(user.getUserId(), accountName);

		// Then
		assertEquals(accountName, accountDto.getAccountName());
		assertEquals(user.getUserId(), accountDto.getUserId());
		assertEquals("yyMM-ddHH-mmssSSS".length(), accountDto.getAccountNumber().length());
		assertEquals(0L, accountDto.getBalance());
	}

	@Test
	@Transactional
	@DisplayName("잔액조회")
	void getAccountBalance() {
		// Given
		AccountService accountService = new AccountServiceImpl(accountRepository, userRepository);
		assertNotNull(accountService);
		Account account = Account.builder()
			.accountName("테스트용 계좌")
			.user(userRepository.save(User.builder()
				.nickname("TEST NAME")
				.birthday(LocalDate.now())
				.role(Role.USER)
				.socialId("123123")
				.socialType(SocialType.KAKAO)
				.build()))
			.accountNumber("123-4567")
			.balance(10000L)
			.build();

		// When
		accountRepository.save(account);

		// Then
		AccountDto accountDto = accountService.getAccountBalance(account.getAccountId());
		assertEquals("123-4567", accountDto.getAccountNumber());
		assertEquals(10000L, accountDto.getBalance());
	}

	@Test
	@DisplayName("계좌명 변경")
	@Transactional
	void changeAccountName() {
		// Given
		AccountService accountService = new AccountServiceImpl(accountRepository, userRepository);
		assertNotNull(accountService);
		Account account = Account.builder()
			.accountName("테스트용 계좌")
			.user(userRepository.save(User.builder()
				.nickname("TEST NAME")
				.birthday(LocalDate.now())
				.role(Role.USER)
				.socialId("123123")
				.socialType(SocialType.KAKAO)
				.build()))
			.accountNumber("123-4567")
			.balance(10000L)
			.build();
		accountRepository.save(account);

		// When
		AccountDto accountDto = AccountDto.builder()
			.accountNumber("123-4567")
			.accountName("지은이 계좌")
			.build();
		accountService.updateAccountName(accountDto);

		// Then
		Account findAccount = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		assertEquals(findAccount.getAccountName(), accountDto.getAccountName());
	}
}
