package com.emotionbank.business.domain.signup.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpDto {
	Long userId;
	String nickname;
	LocalDate birthday;

	@Builder
	public SignUpDto(Long userId, String nickname, LocalDate birthday) {
		this.userId = userId;
		this.nickname = nickname;
		this.birthday = birthday;
	}
}
