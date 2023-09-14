package com.emotionbank.business.domain.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emotionbank.business.domain.auth.dto.JwtTokens;

@SpringBootTest
public class JwtManagerTest {

	@Autowired
	JwtManager jwtManager;

	private static final Long SAMPLE_USER_ID = 1L;

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

}
