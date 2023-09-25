package com.emotionbank.business.domain.signup.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpDto {
	private final Long userId;
	private final String nickname;
	private final LocalDate birthday;

	@Builder
	public SignUpDto(Long userId, String nickname, LocalDate birthday) {
		this.userId = userId;
		this.nickname = nickname;
		this.birthday = birthday;
	}
}
