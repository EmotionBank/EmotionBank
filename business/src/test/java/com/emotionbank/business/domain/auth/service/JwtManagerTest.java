package com.emotionbank.business.domain.auth.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emotionbank.business.domain.auth.dto.JwtTokens;

@SpringBootTest
public class JwtManagerTest {

	@Autowired
	JwtManager jwtManager;

	@Test
	@DisplayName("jwt 토큰을 생성하는 테스트")
	public void createJwtToken() {
		JwtTokens jwtTokens = jwtManager.createJwtTokens(1L);

		assertThat(jwtTokens.getGrantType()).isNotBlank();
		assertThat(jwtTokens.getAccessToken()).isNotBlank();
		assertThat(jwtTokens.getRefreshToken()).isNotBlank();
		assertThat(jwtTokens.getAccessTokenExpirationTime()).isNotNull();
		assertThat(jwtTokens.getRefreshTokenExpirationTime()).isNotNull();
	}

}