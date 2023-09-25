package com.emotionbank.business.api.signup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.signup.dto.RequestSignUpDto;
import com.emotionbank.business.domain.signup.dto.SignUpDto;
import com.emotionbank.business.domain.signup.dto.SignUpUserDto;
import com.emotionbank.business.domain.signup.service.SignUpService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
	private final SignUpService signUpService;

	@PostMapping
	public ResponseEntity<RequestSignUpDto.Response> signup(
		@UserInfo UserInfoDto userInfoDto,
		@RequestBody RequestSignUpDto.Request request) {
		Long userId = userInfoDto.getUserId();

		SignUpUserDto userDto = signUpService.signup(SignUpDto.of(userId, request));

		return ResponseEntity.ok(RequestSignUpDto.Response.from(userDto));
	}
}
