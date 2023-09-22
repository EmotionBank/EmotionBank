package com.emotionbank.business.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDto {
	private static final String GRANT_TYPE_BEARER = "Bearer";

	private String grantType;
	private String accessToken;

	public static AccessTokenDto createBearer(
		String accessToken) {
		return AccessTokenDto.builder()
			.grantType(GRANT_TYPE_BEARER)
			.accessToken(accessToken)
			.build();
	}

}
