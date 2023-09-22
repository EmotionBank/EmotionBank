package com.emotionbank.business.global.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {
	private final Long userId;

	@Builder
	public UserInfoDto(Long userId) {
		this.userId = userId;
	}

	public static UserInfoDto from(Long userId) {
		return UserInfoDto.builder()
			.userId(userId)
			.build();
	}
}
