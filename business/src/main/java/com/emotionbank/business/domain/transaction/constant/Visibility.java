package com.emotionbank.business.domain.transaction.constant;

import lombok.Getter;

@Getter
public enum Visibility {
	PRIVATE("비공개");

	private final String message;

	Visibility(String message) {
		this.message = message;
	}
}
