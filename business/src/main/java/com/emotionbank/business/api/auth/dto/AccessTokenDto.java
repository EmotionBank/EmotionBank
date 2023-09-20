package com.emotionbank.business.api.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenDto {
	private String tokenType;
	private String accessToken;

	@Builder
	public AccessTokenDto(String tokenType, String accessToken) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
	}

	public static AccessTokenDto from(String accessToken) {
		return AccessTokenDto.builder()
			.tokenType("Bearer")
			.accessToken(accessToken)
			.build();
	}
}
