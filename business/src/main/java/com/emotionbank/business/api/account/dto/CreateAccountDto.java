package com.emotionbank.business.api.account.dto;

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

		private Long userId;

		private String accountName;

		private String accountNumber;

		private Long balance;

		@Builder
		public Response(Long accountId, Long userId, String accountName, String accountNumber, Long balance) {
			this.accountId = accountId;
			this.userId = userId;
			this.accountName = accountName;
			this.accountNumber = accountNumber;
			this.balance = balance;
		}
	}
}
