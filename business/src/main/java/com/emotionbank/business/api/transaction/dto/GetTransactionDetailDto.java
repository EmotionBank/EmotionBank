package com.emotionbank.business.api.transaction.dto;

import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GetTransactionDetailDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private Emoticon emoticon;
		private Long amount;
		private String date;
		private String time;
		private String content;
		private String accountName;
		private TransactionType transactionType;
		private String categoryName;
		private Visibility visibility;

		@Builder
		public Response(Emoticon emoticon, Long amount, String date, String time, String content,
			String accountName, TransactionType transactionType, String categoryName, Visibility visibility) {
			this.emoticon = emoticon;
			this.amount = amount;
			this.date = date;
			this.time = time;
			this.content = content;
			this.accountName = accountName;
			this.transactionType = transactionType;
			this.categoryName = categoryName;
			this.visibility = visibility;
		}

		public static Response of(TransactionDto transactionDto, String accountName) {
			return Response.builder()
				.emoticon(transactionDto.getEmoticon())
				.amount(transactionDto.getAmount())
				.date(transactionDto.getTransactionTime().toLocalDate().toString())
				.time(transactionDto.getTransactionTime().toLocalTime().toString())
				.content(transactionDto.getContent())
				.accountName(accountName)
				.transactionType(transactionDto.getTransactionType())
				.categoryName(transactionDto.getCategoryName())
				.visibility(transactionDto.getVisibility())
				.build();
		}
	}
}
