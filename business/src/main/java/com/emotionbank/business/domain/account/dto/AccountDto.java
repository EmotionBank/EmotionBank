package com.emotionbank.business.domain.account.dto;

import java.time.LocalDateTime;

import com.emotionbank.business.domain.account.entity.Account;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountDto
{
	private Long accountId;

	private Long userId;

	private String accountName;

	private String accountNumber;

	private Long balance;

	private LocalDateTime createdTime;

	@Builder
	public AccountDto(Long accountId, Long userId, String accountName, String accountNumber, Long balance,
		LocalDateTime createdTime) {
		this.accountId = accountId;
		this.userId = userId;
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.createdTime = createdTime;
	}

	public static AccountDto from(Account account) {
		return AccountDto.builder()
			.accountId(account.getAccountId())
			.userId(account.getUser().getUserId())
			.accountName(account.getAccountName())
			.accountNumber(account.getAccountNumber())
			.balance(account.getBalance())
			.createdTime(account.getCreatedTime())
			.build();
	}
}
