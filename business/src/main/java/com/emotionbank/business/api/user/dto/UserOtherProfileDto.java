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
		long accountId;
		long userId;
		boolean isFollow;

		public static Response of(UserDto userDto, boolean isFollow) {
			return Response.builder()
				.nickname(userDto.getNickname())
				.following(userDto.getFollowing())
				.follower(userDto.getFollower())
				.userId(userDto.getUserId())
				.accountId(userDto.getAccount().getUserId())
				.isFollow(isFollow)
				.build();
		}
	}
}
