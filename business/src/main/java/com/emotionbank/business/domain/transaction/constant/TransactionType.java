package com.emotionbank.business.domain.transaction.constant;

import lombok.Getter;

@Getter
public enum TransactionType {
	DEPOSIT("입금"),
	WITHDRAWL("출금");

	private final String message;

	TransactionType(String message) {
		this.message = message;
	}
}