package com.emotionbank.business.api.user.dto;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSimpleDto {
	String nickname;
	String image;
	Long userId;

	public static UserSimpleDto from(UserDto userDto) {
		return UserSimpleDto.builder()
			.nickname(userDto.getNickname())
			.image(userDto.getEmail())
			.userId(userDto.getUserId())
			.build();
	}

}
