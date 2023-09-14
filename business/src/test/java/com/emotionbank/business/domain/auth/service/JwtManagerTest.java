package com.emotionbank.business.domain.auth.service;

import static com.emotionbank.business.global.error.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emotionbank.business.domain.auth.dto.JwtTokens;
import com.emotionbank.business.global.error.exception.JwtTokenException;

@SpringBootTest
public class JwtManagerTest {

	@Autowired
	JwtManager jwtManager;

	private static final Long SAMPLE_USER_ID = 1L;
	private static final Long VALID_EXPIRATION_TIME = 100000L;
	private static final Long INVALID_EXPIRATION_TIME = 0L;

	@Test
	@DisplayName("jwt 토큰 생성을 성공한다.")
	public void createJwtToken() {
		// given
		JwtTokens jwtTokens = jwtManager.createJwtTokens(SAMPLE_USER_ID);

		// when & then
		assertThat(jwtTokens.getGrantType()).isNotBlank();
		assertThat(jwtTokens.getAccessToken()).isNotBlank();
		assertThat(jwtTokens.getRefreshToken()).isNotBlank();
		assertThat(jwtTokens.getAccessTokenExpirationTime()).isNotNull();
		assertThat(jwtTokens.getRefreshTokenExpirationTime()).isNotNull();
	}

	@Test
	@DisplayName("accessToken과 refreshToken이 모두 유효한 로직일때 성공한다.")
	public void validateTokenSuccess() {
		// given
		final JwtTokens jwtTokens = jwtManager.createJwtTokens(SAMPLE_USER_ID);

		// when & then
		assertDoesNotThrow(() -> jwtManager.validateTokens(jwtTokens));
	}

	@Test
	@DisplayName("refreshToken의 기한이 만료되었을 때 예외 처리한다.")
	public void refreshToken_Expired() {
		// given
		final JwtTokens jwtTokens = jwtManager.createJwtTokens(SAMPLE_USER_ID);
		final Date refreshTokenExpirationTime = new Date(System.currentTimeMillis() + INVALID_EXPIRATION_TIME);
		final String refreshToken = jwtManager.createRefreshToken(SAMPLE_USER_ID, refreshTokenExpirationTime);
		final Date accessTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String accessToken = jwtManager.createRefreshToken(SAMPLE_USER_ID, accessTokenExpirationTime);
		jwtTokens.setAccessToken(accessToken);
		jwtTokens.setRefreshToken(refreshToken);
		jwtTokens.setAccessTokenExpirationTime(accessTokenExpirationTime);
		jwtTokens.setRefreshTokenExpirationTime(refreshTokenExpirationTime);

		// when & then
		assertThatThrownBy(() -> jwtManager.validateTokens(jwtTokens))
			.isInstanceOf(JwtTokenException.class)
			.hasMessage(REFRESH_TOKEN_EXPIRED.getMessage());
	}

}
