package com.emotionbank.business.domain.auth.service;

import static com.emotionbank.business.global.error.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.security.Key;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emotionbank.business.domain.auth.constant.TokenType;
import com.emotionbank.business.domain.auth.dto.JwtTokens;
import com.emotionbank.business.global.error.exception.JwtTokenException;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
public class JwtManagerTest {

	@Autowired
	JwtManager jwtManager;

	private static final Long SAMPLE_USER_ID = 1L;
	private static final Long VALID_EXPIRATION_TIME = 100000L;
	private static final Long INVALID_EXPIRATION_TIME = 0L;
	private static final String INVALID_SECRET_KEY
		= "abcedfghijklmopqrstuvwxyzabcedfghijklmopqrstuvwxyabcedfghijklmopqrstuvwabcedfghijklmopqrstuv";

	private String createInvalidToken(final String tokenType, final Date expirationTime) {
		final Date now = new Date();
		final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(INVALID_SECRET_KEY));

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setSubject(tokenType)
			.setIssuedAt(now)
			.setExpiration(expirationTime)
			.claim("userId", SAMPLE_USER_ID)
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();
	}

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
	@DisplayName("refreshToken이 유효한 로직일때 성공한다.")
	public void validateRefreshTokenSuccess() {
		// given
		final JwtTokens jwtTokens = jwtManager.createJwtTokens(SAMPLE_USER_ID);

		// when & then
		assertDoesNotThrow(() -> jwtManager.validateRefreshToken(jwtTokens.getRefreshToken()));
	}

	@Test
	@DisplayName("refreshToken의 기한이 만료되었을 때 예외 처리한다.")
	public void refreshToken_Expired() {
		// given
		final Date refreshTokenExpirationTime = new Date(System.currentTimeMillis() + INVALID_EXPIRATION_TIME);
		final String refreshToken = jwtManager.createRefreshToken(SAMPLE_USER_ID, refreshTokenExpirationTime);
		final Date accessTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String accessToken = jwtManager.createRefreshToken(SAMPLE_USER_ID, accessTokenExpirationTime);

		final JwtTokens jwtTokens = JwtTokens.createBearer(accessToken, refreshToken, accessTokenExpirationTime,
			refreshTokenExpirationTime);

		// when & then
		assertThatThrownBy(() -> jwtManager.validateRefreshToken(jwtTokens.getRefreshToken()))
			.isInstanceOf(JwtTokenException.class)
			.hasMessage(REFRESH_TOKEN_EXPIRED.getMessage());
	}

	@Test
	@DisplayName("accessToken의 기한이 만료되었을 때 예외 처리한다.")
	public void accessToken_Expired() {
		// given
		final Date refreshTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String refreshToken = jwtManager.createRefreshToken(SAMPLE_USER_ID, refreshTokenExpirationTime);
		final Date accessTokenExpirationTime = new Date(System.currentTimeMillis() + INVALID_EXPIRATION_TIME);
		final String accessToken = jwtManager.createAccessToken(SAMPLE_USER_ID, accessTokenExpirationTime);

		final JwtTokens jwtTokens = JwtTokens.createBearer(accessToken, refreshToken, accessTokenExpirationTime,
			refreshTokenExpirationTime);

		// when & then
		assertThatThrownBy(() -> jwtManager.validateAccessToken(jwtTokens.getAccessToken()))
			.isInstanceOf(JwtTokenException.class)
			.hasMessage(ACCESS_TOKEN_EXPIRED.getMessage());
	}

	@Test
	@DisplayName("refreshToken이 유효하지 않은 형식일 때 예외처리 한다.")
	public void refreshToken_Invalid() {
		// given
		final Date refreshTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String refreshToken = createInvalidToken(TokenType.REFRESH.name(), refreshTokenExpirationTime);
		final Date accessTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String accessToken = jwtManager.createAccessToken(SAMPLE_USER_ID, accessTokenExpirationTime);

		final JwtTokens jwtTokens = JwtTokens.createBearer(accessToken, refreshToken, accessTokenExpirationTime,
			refreshTokenExpirationTime);

		// when & then
		assertThatThrownBy(() -> jwtManager.validateRefreshToken(jwtTokens.getRefreshToken()))
			.isInstanceOf(JwtTokenException.class)
			.hasMessage(REFRESH_TOKEN_INVALID.getMessage());
	}

	@Test
	@DisplayName("accessToken이 유효하지 않은 형식일 때 예외처리 한다.")
	public void accessToken_Invalid() {
		// given
		final Date refreshTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String refreshToken = jwtManager.createRefreshToken(SAMPLE_USER_ID, refreshTokenExpirationTime);
		final Date accessTokenExpirationTime = new Date(System.currentTimeMillis() + VALID_EXPIRATION_TIME);
		final String accessToken = createInvalidToken(TokenType.ACCESS.name(), accessTokenExpirationTime);

		final JwtTokens jwtTokens = JwtTokens.createBearer(accessToken, refreshToken, accessTokenExpirationTime,
			refreshTokenExpirationTime);

		// when & then
		assertThatThrownBy(() -> jwtManager.validateAccessToken(jwtTokens.getAccessToken()))
			.isInstanceOf(JwtTokenException.class)
			.hasMessage(ACCESS_TOKEN_INVALID.getMessage());
	}

}
