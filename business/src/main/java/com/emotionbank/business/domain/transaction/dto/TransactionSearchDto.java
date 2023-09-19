package com.emotionbank.business.domain.transaction.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.emotionbank.business.api.transaction.dto.GetTransactionListDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionSearchDto {
	private String accountNumber;
	private Date date;

	@Builder
	public TransactionSearchDto(String accountNumber, Date date) {
		this.accountNumber = accountNumber;
		this.date = date;
	}

	public static TransactionSearchDto from(GetTransactionListDto.Request request) {
		return TransactionSearchDto.builder()
			.accountNumber(request.getAccountNumber())
			.date(Date.valueOf(LocalDate.parse(request.getDate())))
			.build();
	}
}
