package com.emotionbank.business.api.user.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.Builder;

@Builder
public class UserSimpleDto {
	String nickname;
	String image;

	public static UserSimpleDto from(UserDto userDto) {
		return UserSimpleDto.builder()
			.nickname(userDto.getNickname())
			.image(userDto.getEmail())
			.build();
	}

	public static List<UserSimpleDto> from(List<UserDto> userDtos) {
		return userDtos.stream().map(UserSimpleDto::from).collect(Collectors.toList());
	}
}
