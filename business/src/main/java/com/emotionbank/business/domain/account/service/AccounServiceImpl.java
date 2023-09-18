package com.emotionbank.business.domain.account.service;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccounServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	@Override
	public AccountDto createAccount(Long userId, String accountName) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Account account = accountRepository.save(Account.of(accountName, user));
		return AccountDto.from(account);
	}

	@Override
	public AccountDto getAccountBalance(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		return AccountDto.from(account);
	}
}
