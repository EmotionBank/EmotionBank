package com.emotionbank.business.api.user.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserSearchDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		List<UserSimpleDto> users;

		@Builder
		public Response(List<UserSimpleDto> users) {
			this.users = users;
		}

		public static Response from(List<UserDto> userDtos) {
			return Response.builder()
				.users(userDtos.stream().map(UserSimpleDto::from).collect(Collectors.toList()))
				.build();
		}
	}
}
