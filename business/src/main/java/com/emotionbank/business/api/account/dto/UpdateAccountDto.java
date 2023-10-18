package com.emotionbank.business.api.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateAccountDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String accountNumber;
		private String accountName;

		@Builder
		public Request(String accountNumber, String accountName) {
			this.accountNumber = accountNumber;
			this.accountName = accountName;
		}
	}
}
