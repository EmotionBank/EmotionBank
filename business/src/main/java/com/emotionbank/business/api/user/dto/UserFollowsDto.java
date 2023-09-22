package com.emotionbank.business.api.user.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserFollowsDto {

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		List<UserSimpleDto> followees;

		public static Response from(List<UserDto> userDtos) {
			return Response.builder()
				.followees(userDtos.stream().map(UserSimpleDto::from).collect(Collectors.toList()))
				.build();
		}
	}

}
