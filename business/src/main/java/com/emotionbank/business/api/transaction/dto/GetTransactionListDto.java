package com.emotionbank.business.api.transaction.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GetTransactionListDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private Long accountId;
		private String startDate;
		private String endDate;

		@Builder
		public Request(Long accountId, String startDate, String endDate) {
			this.accountId = accountId;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Info {
		private Long transactionId;
		private Emoticon emoticon;
		private LocalDateTime date;
		private TransactionType transactionType;
		private String title;
		private Long amount;
		private String categoryName;
		private Visibility visibility;

		@Builder
		public Info(Long transactionId, Emoticon emoticon, LocalDateTime date, TransactionType transactionType,
			String title,
			Long amount, String categoryName, Visibility visibility) {
			this.transactionId = transactionId;
			this.emoticon = emoticon;
			this.date = date;
			this.transactionType = transactionType;
			this.title = title;
			this.amount = amount;
			this.categoryName = categoryName;
			this.visibility = visibility;
		}

		public static Info from(TransactionDto transactionDto) {
			return Info.builder()
				.transactionId(transactionDto.getTransactionId())
				.emoticon(transactionDto.getEmoticon())
				.date(transactionDto.getTransactionTime())
				.transactionType(transactionDto.getTransactionType())
				.title(transactionDto.getTitle())
				.amount(transactionDto.getAmount())
				.categoryName(transactionDto.getCategoryName())
				.visibility(transactionDto.getVisibility())
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
