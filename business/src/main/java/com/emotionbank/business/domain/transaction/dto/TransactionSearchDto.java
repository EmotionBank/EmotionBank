package com.emotionbank.business.domain.transaction.dto;

import java.sql.Date;
import java.time.LocalDate;

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

	public static TransactionSearchDto of(String accountNumber, String date) {
		return TransactionSearchDto.builder()
			.accountNumber(accountNumber)
			.date(Date.valueOf(LocalDate.parse(date)))
			.build();
	}
}
