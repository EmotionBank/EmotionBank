package com.emotionbank.business.api.auth.controller;

import static org.springframework.http.HttpHeaders.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.auth.dto.LoginAccessTokenDto;
import com.emotionbank.business.api.auth.dto.RenewalAccessTokenDto;
import com.emotionbank.business.domain.auth.dto.AccessTokenDto;
import com.emotionbank.business.domain.auth.dto.JwtTokens;
import com.emotionbank.business.domain.auth.dto.LoginJwtDto;
import com.emotionbank.business.domain.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	public static final int COOKIE_AGE_SECOND = 1209600;

	private final AuthService authService;

	@GetMapping("/login/{loginType}/callback")
	public ResponseEntity<LoginAccessTokenDto> login(
		@PathVariable final String loginType,
		String code,
		final HttpServletResponse response) {
		final LoginJwtDto loginJwtDto = authService.loginOrRegister(loginType, code);
		final JwtTokens jwtTokens = loginJwtDto.getJwtTokens();
		final ResponseCookie cookie = ResponseCookie.from("refresh-token", jwtTokens.getRefreshToken())
			.maxAge(COOKIE_AGE_SECOND)
			.sameSite("None")
			.secure(true)
			.httpOnly(true)
			.path("/")
			.build();
		response.addHeader(SET_COOKIE, cookie.toString());
		return ResponseEntity.ok(
			LoginAccessTokenDto.of(RenewalAccessTokenDto.from(jwtTokens.getAccessToken()), loginJwtDto.getRole()));
	}

	@GetMapping("/token")
	public ResponseEntity<RenewalAccessTokenDto> renewalAccessToken(
		@CookieValue("refresh-token") final String refreshToken,
		@RequestHeader("Authorization") final String authorizationHeader) {

		AccessTokenDto accessTokenDto = authService.renewalAccessToken(refreshToken, authorizationHeader);

		return ResponseEntity.ok(RenewalAccessTokenDto.from(accessTokenDto.getAccessToken()));
	}

}
