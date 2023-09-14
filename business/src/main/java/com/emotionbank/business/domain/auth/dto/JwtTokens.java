package com.emotionbank.business.domain.auth.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokens {

	private static String GRANT_TYPE_BEARER = "Bearer";

	private String grantType;
	private String accessToken;
	private Date accessTokenExpirationTime;
	private String refreshToken;
	private Date refreshTokenExpirationTime;

	public static JwtTokens createBearer(
		String accessToken,
		String refreshToken,
		Date accessTokenExpirationTime,
		Date refreshTokenExpirationTime) {
		return JwtTokens.builder()
			.grantType(GRANT_TYPE_BEARER)
			.accessToken(accessToken)
			.accessTokenExpirationTime(accessTokenExpirationTime)
			.refreshToken(refreshToken)
			.refreshTokenExpirationTime(refreshTokenExpirationTime)
			.build();
	}

}
