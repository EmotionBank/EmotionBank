package com.emotionbank.business.api.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserNicknameCheckDto {

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		String nickname;
	}

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		boolean isPossible;

		public static Response of(boolean isPossible) {
			return Response.builder()
				.isPossible(isPossible)
				.build();
		}
	}
}
