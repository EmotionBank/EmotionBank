package com.emotionbank.business.api.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserNicknameCheckDto {

	@Builder
	@Getter
	public static class Request {
		String nickname;
	}

	@Builder
	@Getter
	public static class Response {
		boolean isPossible;

		public static Response of(boolean isPossible) {
			return Response.builder()
				.isPossible(isPossible)
				.build();
		}
	}
}
