package com.emotionbank.business.api.user.dto;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserMyProfileDto {
	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		String nickname;
		Long accountId;
		String accountNumber;
		Long balance;
		int following;
		int follower;
		long userId;

		public static Response from(UserDto userDto) {
			return Response.builder()
				.nickname(userDto.getNickname())
				.accountId(userDto.getAccount().getAccountId())
				.accountNumber(userDto.getAccount().getAccountNumber())
				.balance(userDto.getAccount().getBalance())
				.following(userDto.getFollowing())
				.follower(userDto.getFollower())
				.userId(userDto.getUserId())
				.build();
		}
	}
}
