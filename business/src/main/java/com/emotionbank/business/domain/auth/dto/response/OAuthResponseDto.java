package com.emotionbank.business.domain.auth.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthResponseDto {
	private String tokenType;
	private String accessToken;
	private Integer expiresIn;
	private String scope;

	@Builder
	public OAuthResponseDto(String tokenType, String accessToken, Integer expiresIn, String scope) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.scope = scope;
	}
}
