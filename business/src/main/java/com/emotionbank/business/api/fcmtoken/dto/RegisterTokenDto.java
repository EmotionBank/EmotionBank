package com.emotionbank.business.api.fcmtoken.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RegisterTokenDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String token;

		@Builder
		public Request(String token) {
			this.token = token;
		}
	}
}
