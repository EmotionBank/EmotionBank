package com.emotionbank.business.api.auth.controller;

import static org.springframework.http.HttpHeaders.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.account.dto.CreateAccountDto;
import com.emotionbank.business.api.auth.dto.LoginAccessTokenDto;
import com.emotionbank.business.api.auth.dto.RenewalAccessTokenDto;
import com.emotionbank.business.api.auth.dto.RequestSignUpDto;
import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.service.AccountService;
import com.emotionbank.business.domain.auth.dto.AccessTokenDto;
import com.emotionbank.business.domain.auth.dto.JwtTokens;
import com.emotionbank.business.domain.auth.dto.LoginJwtDto;
import com.emotionbank.business.domain.auth.dto.SignUpDto;
import com.emotionbank.business.domain.auth.dto.SignUpUserDto;
import com.emotionbank.business.domain.auth.service.AuthService;
import com.emotionbank.business.domain.category.dto.CategoryDto;
import com.emotionbank.business.domain.category.service.CategoryService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	public static final int COOKIE_AGE_SECOND = 1209600;

	private final AuthService authService;
	private final AccountService accountService;
	private final CategoryService categoryService;

	@GetMapping("/auth/login/{loginType}/callback")
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

	@PostMapping("/signup")
	public ResponseEntity<RequestSignUpDto.Response> signup(
		@UserInfo UserInfoDto userInfoDto,
		@RequestBody RequestSignUpDto.Request request) {
		Long userId = userInfoDto.getUserId();

		SignUpUserDto userDto = authService.signup(SignUpDto.of(userId, request));
		AccountDto account = accountService.createAccount(userId, request.getAccountName());

		createDefaultCategories(userId);

		return ResponseEntity.ok(RequestSignUpDto.Response.of(userDto, CreateAccountDto.Response.from(account)));
	}

	@DeleteMapping("/logout")
	public ResponseEntity<Void> logout(@UserInfo UserInfoDto userInfoDto, final HttpServletResponse response) {
		authService.removeRefreshToken(userInfoDto.getUserId());

		final ResponseCookie cookie = ResponseCookie.from("refresh-token", "")
			.maxAge(0)
			.sameSite("None")
			.secure(true)
			.httpOnly(true)
			.path("/")
			.build();
		response.addHeader(SET_COOKIE, cookie.toString());

		return ResponseEntity.noContent().build();
	}

	private void createDefaultCategories(Long userId) {
		categoryService.createCategory(CategoryDto.newDefaultCategory(userId));
		categoryService.createCategory(CategoryDto.newTransactionCategory(userId));
	}

}
