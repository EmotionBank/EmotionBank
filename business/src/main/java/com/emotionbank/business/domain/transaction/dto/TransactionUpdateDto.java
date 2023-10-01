package com.emotionbank.business.domain.transaction.dto;

import com.emotionbank.business.api.transaction.dto.UpdateTransactionDto;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionUpdateDto {
	private Long transactionId;
	private Long categoryId;
	private Long userId;

	@Builder
	public TransactionUpdateDto(Long transactionId, Long categoryId, Long userId) {
		this.transactionId = transactionId;
		this.categoryId = categoryId;
		this.userId = userId;
	}

	public static TransactionUpdateDto of(UpdateTransactionDto.Request request, UserInfoDto userInfoDto) {
		return TransactionUpdateDto.builder()
			.transactionId(request.getTransactionId())
			.categoryId(request.getCategoryId())
			.userId(userInfoDto.getUserId())
			.build();
	}
}
