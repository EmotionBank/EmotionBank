package com.emotionbank.business.domain.account.service;

import com.emotionbank.business.domain.account.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(Long userId, String accountName);

	AccountDto getAccountBalance(String accountNumber);
}
