package com.emotionbank.business.api.user.dto;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserOtherProfileDto {

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		String nickname;
		int following;
		int follower;

		public static Response from(UserDto userDto) {
			return Response.builder()
				.nickname(userDto.getNickname())
				.following(userDto.getFollowing())
				.follower(userDto.getFollower())
				.build();
		}
	}
}
