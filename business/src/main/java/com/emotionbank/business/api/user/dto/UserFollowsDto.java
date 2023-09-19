package com.emotionbank.business.api.user.dto;

import java.util.List;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.Builder;

public class UserFollowsDto {

	@Builder
	public static class Response {
		List<UserSimpleDto> followees;

		public static Response from(List<UserDto> userDtos) {
			return Response.builder()
				.followees(UserSimpleDto.from(userDtos))
				.build();
		}
	}

}
