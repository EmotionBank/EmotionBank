package com.emotionbank.business.api.signup.dto;

import com.emotionbank.business.domain.signup.dto.SignUpUserDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RequestSignUpDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String nickname;
		private String birthday;

		@Builder
		public Request(String nickname, String birthday) {
			this.nickname = nickname;
			this.birthday = birthday;
		}
	}

	@Getter
	public static class Response {
		private final Long userId;
		private final String nickname;
		private final String birthday;
		private final String role;

		@Builder
		public Response(Long userId, String nickname, String birthday, String role) {
			this.userId = userId;
			this.nickname = nickname;
			this.birthday = birthday;
			this.role = role;
		}

		public static Response from(SignUpUserDto signUpUserDto) {
			return Response.builder()
				.userId(signUpUserDto.getUserId())
				.nickname(signUpUserDto.getNickname())
				.birthday(signUpUserDto.getBirthDay().toString())
				.role(signUpUserDto.getRole())
				.build();
		}
	}
}
