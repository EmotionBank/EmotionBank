package com.emotionbank.business.domain.transaction.constant;

import lombok.Getter;

@Getter
public enum Emoticon {
	HAPPY("기쁨"),
	EXCITING("신남"),
	FLUTTER("설렘"),
	SOSO("그저그럼"),
	SAD("슬픔"),
	TIRED("피곤"),
	ANNOYING("짜증"),
	ANGRY("화남"),
	GLOOMY("우울");

	private final String message;

	Emoticon(String message) {
		this.message = message;
	}
}