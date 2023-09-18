package com.emotionbank.business.domain.auth.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthRequestDto {
	private final String GRANT_TYPE = "authorization_code";
	private final String clientId;
	private final String clientSecret;
	private final String redirectUri;
	private final String code;

	@Builder
	public OAuthRequestDto(String clientId, String clientSecret, String redirectUri, String code) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		this.code = code;
	}

	public static OAuthRequestDto of(String clientId, String clientSecret, String redirectUri, String code) {
		return OAuthRequestDto.builder()
			.clientId(clientId)
			.clientSecret(clientSecret)
			.redirectUri(redirectUri)
			.code(code)
			.build();
	}
}
