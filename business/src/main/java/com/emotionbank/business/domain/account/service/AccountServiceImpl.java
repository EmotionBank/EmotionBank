package com.emotionbank.business.domain.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public AccountDto createAccount(Long userId, String accountName) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Account account = accountRepository.save(Account.of(accountName, user));
		return AccountDto.from(account);
	}

	@Override
	@Transactional(readOnly = true)
	public AccountDto getAccountBalance(Long accountId) {
		Account account = accountRepository.findByAccountId(accountId)
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		return AccountDto.from(account);
	}

	@Override
	@Transactional
	public void updateAccountName(AccountDto accountDto) {
		Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		account.updateAccountName(accountDto.getAccountName());
	}
}
