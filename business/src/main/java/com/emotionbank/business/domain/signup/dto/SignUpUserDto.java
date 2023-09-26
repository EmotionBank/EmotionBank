package com.emotionbank.business.domain.signup.dto;

import java.time.LocalDate;

import com.emotionbank.business.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpUserDto {
	private final Long userId;

	private final String nickname;

	private final LocalDate birthDay;

	private final String role;

	@Builder
	public SignUpUserDto(Long userId, String nickname, LocalDate birthDay, String role) {
		this.userId = userId;
		this.nickname = nickname;
		this.birthDay = birthDay;
		this.role = role;
	}

	public static SignUpUserDto from(User user) {
		return SignUpUserDto.builder()
			.userId(user.getUserId())
			.nickname(user.getNickname())
			.birthDay(user.getBirthday())
			.role(user.getRole().name())
			.build();
	}
}
