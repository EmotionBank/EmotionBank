package com.emotionbank.business.api.terms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.terms.dto.GetTermsDto;
import com.emotionbank.business.api.terms.dto.TermsCreateDto;
import com.emotionbank.business.domain.terms.dto.TermsDto;
import com.emotionbank.business.domain.terms.service.TermsService;
import com.emotionbank.business.domain.user.service.UserService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/terms")
@RequiredArgsConstructor
public class TermsController {

	private final TermsService termsService;
	private final UserService userService;

	@PostMapping
	public ResponseEntity<TermsCreateDto.Response> createTerms(@UserInfo UserInfoDto userInfoDto,
		@RequestBody TermsCreateDto.Request request) {
		if (!userService.checkAdminRole(userInfoDto)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		TermsDto termsDto = termsService.createTerms(TermsDto.from(request));
		return ResponseEntity.ok(TermsCreateDto.Response.from(termsDto));
	}

	@GetMapping
	public ResponseEntity<GetTermsDto> getTerms() {
		List<TermsDto> termsDto = termsService.getTerms();

		return ResponseEntity.ok(GetTermsDto.from(termsDto));
	}

}
