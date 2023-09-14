package com.emotionbank.business.domain.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.emotionbank.business.domain.auth.constant.TokenType;
import com.emotionbank.business.domain.auth.dto.JwtTokens;
import com.emotionbank.business.global.properties.JwtProperties;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtManager {

	private final JwtProperties jwtProperties;

	public JwtTokens createJwtTokens(Long userId) {
		Date accessTokenExpireTime = createAccessTokenExpireTime();
		Date refreshTokenExpireTime = createRefreshTokenExpireTime();
		final String accessToken = createAccessToken(userId, accessTokenExpireTime);
		final String refreshToken = createRefreshToken(userId, refreshTokenExpireTime);
		return JwtTokens.createBearer(
			accessToken,
			refreshToken,
			accessTokenExpireTime,
			refreshTokenExpireTime
		);
	}

	public String createAccessToken(final Long userId, final Date expirationTime) {
		return createToken(TokenType.ACCESS.name(), userId, expirationTime);
	}

	public String createRefreshToken(final Long userId, final Date expirationTime) {
		return createToken(TokenType.REFRESH.name(), userId, expirationTime);
	}

	private String createToken(String tokenType, Long userId, Date expirationTime) {
		final Date now = new Date();

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setSubject(tokenType)
			.setIssuedAt(now)
			.setExpiration(expirationTime)
			.claim("userId", userId)
			.signWith(SignatureAlgorithm.ES512, jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))
			.setHeaderParam("typ", "JWT")
			.compact();
	}

	private Date createAccessTokenExpireTime() {
		return new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExpirationTime());
	}

	private Date createRefreshTokenExpireTime() {
		return new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenExpirationTime());
	}
}
