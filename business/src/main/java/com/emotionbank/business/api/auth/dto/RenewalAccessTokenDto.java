package com.emotionbank.business.api.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RenewalAccessTokenDto {
	private String tokenType;
	private String accessToken;

	@Builder
	public RenewalAccessTokenDto(String tokenType, String accessToken) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
	}

	public static RenewalAccessTokenDto from(String accessToken) {
		return RenewalAccessTokenDto.builder()
			.tokenType("Bearer")
			.accessToken(accessToken)
			.build();
	}
}
