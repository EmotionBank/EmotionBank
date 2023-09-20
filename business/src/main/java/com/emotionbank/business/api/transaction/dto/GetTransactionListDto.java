package com.emotionbank.business.api.transaction.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.transaction.dto.TransactionDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GetTransactionListDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String accountNumber;
		private String date;

		@Builder
		public Request(String accountNumber, String date) {
			this.accountNumber = accountNumber;
			this.date = date;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Info {
		private Long transactionId;
		private String emoticon;
		private LocalDateTime date;
		private String transactionType;
		private String title;
		private Long amount;

		@Builder
		public Info(Long transactionId, String emoticon, LocalDateTime date, String transactionType, String title,
			Long amount) {
			this.transactionId = transactionId;
			this.emoticon = emoticon;
			this.date = date;
			this.transactionType = transactionType;
			this.title = title;
			this.amount = amount;
		}

		public static Info from(TransactionDto transactionDto) {
			return Info.builder()
				.transactionId(transactionDto.getTransactionId())
				.emoticon(transactionDto.getEmoticon().getMessage())
				.date(transactionDto.getTransactionTime())
				.transactionType(transactionDto.getTransactionType().getMessage())
				.title(transactionDto.getTitle())
				.amount(transactionDto.getAmount())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private List<Info> transactionInfoList;

		@Builder
		public Response(List<Info> transactionInfoList) {
			this.transactionInfoList = transactionInfoList;
		}

		public static Response from(List<TransactionDto> transactionDtoList) {
			return Response.builder()
				.transactionInfoList(transactionDtoList.stream()
					.map(Info::from)
					.collect(Collectors.toList())
				)
				.build();
		}
	}

}
