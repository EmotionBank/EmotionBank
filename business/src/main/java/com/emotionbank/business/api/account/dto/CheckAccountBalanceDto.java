package com.emotionbank.business.api.account.dto;

import com.emotionbank.business.domain.account.dto.AccountDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CheckAccountBalanceDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String accountNumber;

		@Builder
		public Request(String accountNumber) {
			this.accountNumber = accountNumber;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private String accountName;
		private Long balance;

		@Builder
		public Response(String accountName, Long balance) {
			this.accountName = accountName;
			this.balance = balance;
		}

		public static Response from(AccountDto accountDto){
			return Response.builder()
				.accountName(accountDto.getAccountName())
				.balance(accountDto.getBalance())
				.build();
		}
	}



}
