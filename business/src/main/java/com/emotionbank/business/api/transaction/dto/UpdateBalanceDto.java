package com.emotionbank.business.api.transaction.dto;

import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateBalanceDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String transactionType;
		private Long categoryId;
		private Long amount;
		private Long balance;
		private String content;
		private String emoticon;
		private String accountNumber;

		@Builder
		public Request(String transactionType, Long categoryId, Long amount, Long balance, String content,
			String emoticon,
			String accountNumber) {
			this.transactionType = transactionType;
			this.categoryId = categoryId;
			this.amount = amount;
			this.balance = balance;
			this.content = content;
			this.emoticon = emoticon;
			this.accountNumber = accountNumber;
		}

	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private String accountName;
		private Long amount;
		private Long balance;
		private TransactionType transactionType;
		private String content;

		@Builder
		public Response(String accountName, Long amount, Long balance, TransactionType transactionType,
			String content) {
			this.accountName = accountName;
			this.amount = amount;
			this.balance = balance;
			this.transactionType = transactionType;
			this.content = content;
		}

		public static Response from(TransactionDto transactionDto) {
			return Response.builder()
				.accountName(transactionDto.getReceiver())
				.amount(transactionDto.getAmount())
				.balance(transactionDto.getBalance())
				.transactionType(transactionDto.getTransactionType())
				.content(transactionDto.getContent())
				.build();
		}
	}
}
