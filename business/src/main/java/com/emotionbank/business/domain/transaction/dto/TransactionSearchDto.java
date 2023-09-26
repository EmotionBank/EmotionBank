package com.emotionbank.business.domain.transaction.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.emotionbank.business.api.transaction.dto.GetTransactionListDto;

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

	public static TransactionSearchDto of(GetTransactionListDto.Request request, Long userId) {
		return TransactionSearchDto.builder()
			.accountId(request.getAccountId())
			.userId(userId)
			.startDate(Date.valueOf(LocalDate.parse(request.getStartDate())))
			.endDate(Date.valueOf(LocalDate.parse(request.getEndDate())))
			.build();
	}
}
