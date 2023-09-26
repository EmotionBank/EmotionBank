package com.emotionbank.business.domain.transaction.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionSearchDto {
	private Long accountId;
	private Long userId;
	private Date startDate;
	private Date endDate;

	@Builder
	public TransactionSearchDto(Long accountId, Long userId, Date startDate, Date endDate) {
		this.accountId = accountId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static TransactionSearchDto of(Long accountId, Long userId, String startDate, String endDate) {
		return TransactionSearchDto.builder()
			.accountId(accountId)
			.userId(userId)
			.startDate(Date.valueOf(LocalDate.parse(startDate)))
			.endDate(Date.valueOf(LocalDate.parse(endDate)))
			.build();
	}
}
