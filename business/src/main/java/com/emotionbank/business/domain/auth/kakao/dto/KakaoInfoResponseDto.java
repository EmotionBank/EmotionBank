package com.emotionbank.business.domain.auth.kakao.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class KakaoInfoResponseDto {
	@JsonProperty("id")
	private String kakaoId;

	@JsonProperty("kakao_account")
	private KakaoAccount kakaoAccount;

	@JsonProperty("connected_at")
	private LocalDateTime connectedAt;

	@Getter
	@Setter
	@ToString
	public static class KakaoAccount {

		private Profile profile;

		@Getter
		@Setter
		@ToString
		public static class Profile {

			private String nickname;
		}

	}
}