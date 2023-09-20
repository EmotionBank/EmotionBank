package com.emotionbank.business.domain.transaction.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionSearchDto {
	private Long accountId;
	private Date startDate;
	private Date endDate;

	@Builder
	public TransactionSearchDto(Long accountId, Date startDate, Date endDate) {
		this.accountId = accountId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static TransactionSearchDto of(Long accountId, String startDate, String endDate) {
		return TransactionSearchDto.builder()
			.accountId(accountId)
			.startDate(Date.valueOf(LocalDate.parse(startDate)))
			.endDate(Date.valueOf(LocalDate.parse(endDate)))
			.build();
	}
}
