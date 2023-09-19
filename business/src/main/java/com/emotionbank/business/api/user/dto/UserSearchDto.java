package com.emotionbank.business.api.user.dto;

import java.util.List;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.Builder;

public class UserSearchDto {

	@Builder
	public static class Response {
		List<UserSimpleDto> users;

		public static Response from(List<UserDto> userDtos) {
			return Response.builder()
				.users(UserSimpleDto.from(userDtos)).build();
		}
	}
}
