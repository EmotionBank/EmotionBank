package com.emotionbank.business.domain.signup.dto;

import java.time.LocalDate;

import com.emotionbank.business.api.signup.dto.RequestSignUpDto;

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

	public static SignUpDto of(Long userId, RequestSignUpDto.Request request) {
		String[] birth = request.getBirthday().split("-");
		return SignUpDto.builder()
			.userId(userId)
			.nickname(request.getNickname())
			.birthday(LocalDate.of(Integer.parseInt(birth[0]), Integer.parseInt(birth[1]), Integer.parseInt(birth[2])))
			.build();
	}
}
