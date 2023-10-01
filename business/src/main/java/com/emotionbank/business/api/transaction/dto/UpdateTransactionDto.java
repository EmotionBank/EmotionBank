package com.emotionbank.business.api.transaction.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateTransactionDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private Long transactionId;
		private Long categoryId;

		@Builder
		public Request(Long transactionId, Long categoryId) {
			this.transactionId = transactionId;
			this.categoryId = categoryId;
		}
	}
}
