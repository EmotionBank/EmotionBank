package com.emotionbank.business.domain.transaction.constant;

import lombok.Getter;

@Getter
public enum Emoticon {
	HAPPY("기쁨");

	private final String message;

	Emoticon(String message) {
		this.message = message;
	}
}