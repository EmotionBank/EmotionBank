package com.emotionbank.business.domain.transaction.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionSearchDto {
	private String accountNumber;
	private Date startDate;
	private Date endDate;

	@Builder
	public TransactionSearchDto(String accountNumber, Date startDate, Date endDate) {
		this.accountNumber = accountNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static TransactionSearchDto from(Map<String, String> paramMap) {
		return TransactionSearchDto.builder()
			.accountNumber(paramMap.get("accountNumber"))
			.startDate(Date.valueOf(LocalDate.parse(paramMap.get("startDate"))))
			.endDate(Date.valueOf(LocalDate.parse(paramMap.get("endDate"))))
			.build();
	}
}
