package com.emotionbank.business.api.account.dto;

import com.emotionbank.business.domain.account.dto.AccountDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateAccountDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String accountName;

		@Builder
		public Request(String accountName) {
			this.accountName = accountName;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {

		private Long accountId;

		private String accountName;

		private String accountNumber;

		private Long balance;

		@Builder
		public Response(Long accountId, Long userId, String accountName, String accountNumber, Long balance) {
			this.accountId = accountId;
			this.accountName = accountName;
			this.accountNumber = accountNumber;
			this.balance = balance;
		}

		public static Response from(AccountDto accountDto) {
			return Response.builder()
				.accountId(accountDto.getAccountId())
				.accountName(accountDto.getAccountName())
				.accountNumber(accountDto.getAccountNumber())
				.balance(accountDto.getBalance())
				.build();
		}
	}
}
