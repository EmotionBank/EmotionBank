package com.emotionbank.business.api.user.dto;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.Builder;

public class UserSearchDto {

	@Builder
	public static class Response {
		private String nickname;
		private String image;

		public static Response of(UserDto userDto) {
			return Response.builder()
				.nickname(userDto.getNickname())
				.image(userDto.getImage())
				.build();
		}
	}
}
