package com.emotionbank.business.api.user.dto;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.Builder;

@Builder
public class UserSimpleDto {
	String nickname;
	String image;

	public static UserSimpleDto of(UserDto userDto) {
		return UserSimpleDto.builder()
			.nickname(userDto.getNickname())
			.image(userDto.getEmail())
			.build();
	}
}
