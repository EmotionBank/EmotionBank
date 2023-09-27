package com.emotionbank.business.api.transaction.dto;

import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TransferDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Builder
	public static class Request {
		private Long receiver;
		private Long amount;
		private Emoticon emoticon;
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Builder
	public static class Response {
		private String receiver;
		private Long amount;
		private Long balance;

		public static Response of(TransactionDto transactionDto) {
			return Response.builder()
				.receiver(transactionDto.getReceiver())
				.amount(transactionDto.getAmount())
				.balance(transactionDto.getBalance())
				.build();
		}
	}
}
