package com.emotionbank.business.api.fcmtoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.fcmtoken.dto.RegisterTokenDto;
import com.emotionbank.business.domain.fcmtoken.dto.FcmTokenDto;
import com.emotionbank.business.domain.fcmtoken.service.FcmTokenService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;
import com.google.firebase.messaging.FirebaseMessagingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fcmtoken")
@RequiredArgsConstructor
public class FcmTokenController {

	private final FcmTokenService tokenService;

	@PostMapping
	public ResponseEntity<?> registerToken(@RequestBody RegisterTokenDto.Request request, @UserInfo UserInfoDto userInfoDto) throws
		FirebaseMessagingException {
		tokenService.createToken(FcmTokenDto.of(request, userInfoDto.getUserId()));
		return ResponseEntity.ok().build();
	}

}
