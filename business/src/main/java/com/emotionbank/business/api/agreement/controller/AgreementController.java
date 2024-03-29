package com.emotionbank.business.api.agreement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.agreement.dto.TermsAgreementDto;
import com.emotionbank.business.domain.agreement.dto.AgreementDto;
import com.emotionbank.business.domain.agreement.service.AgreementService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agreement")
@RequiredArgsConstructor
public class AgreementController {
	private final AgreementService agreementService;

	@PutMapping
	public ResponseEntity<TermsAgreementDto.ResponseList> agreementTerms(@UserInfo UserInfoDto userInfoDto,
		@RequestBody TermsAgreementDto.RequestList requests) {
		List<TermsAgreementDto.Response> responses = requests.getRequests().stream()
			.map(request -> TermsAgreementDto.Response.from(
				agreementService.updateAgreement(AgreementDto.of(userInfoDto, request))))
			.collect(Collectors.toList());

		return ResponseEntity.ok(TermsAgreementDto.ResponseList.from(responses));
	}

	@GetMapping
	public ResponseEntity<TermsAgreementDto.ResponseList> getAgreement(@UserInfo UserInfoDto userInfoDto) {
		List<TermsAgreementDto.Response> responses = agreementService.getAgreementList(userInfoDto.getUserId())
			.stream()
			.map(TermsAgreementDto.Response::from)
			.collect(Collectors.toList());

		return ResponseEntity.ok(TermsAgreementDto.ResponseList.from(responses));
	}

}
