package com.emotionbank.business.domain.agreement.dto;

import com.emotionbank.business.api.agreement.dto.TermsAgreementDto;
import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AgreementDto {
	private final Long agreementId;
	private final Long userId;
	private final Long termsId;
	private final String title;
	private final String content;
	private final String mandatory;
	private final String state;
	private final String agreementTime;

	@Builder
	public AgreementDto(Long agreementId, Long userId, Long termsId, String title, String content, String mandatory,
		String state, String agreementTime) {
		this.agreementId = agreementId;
		this.userId = userId;
		this.termsId = termsId;
		this.title = title;
		this.content = content;
		this.mandatory = mandatory;
		this.state = state;
		this.agreementTime = agreementTime;
	}

	public static AgreementDto from(Agreement agreement) {
		return AgreementDto.builder()
			.agreementId(agreement.getAgreementId())
			.userId(agreement.getUser().getUserId())
			.termsId(agreement.getTerms().getTermsId())
			.title(agreement.getTerms().getTitle())
			.content(agreement.getTerms().getContent())
			.mandatory(agreement.getTerms().getMandatory().name())
			.state(agreement.getState().name())
			.agreementTime(agreement.getAgreementTime().toString())
			.build();
	}

	public static AgreementDto of(UserInfoDto userInfoDto, TermsAgreementDto.Request request) {
		return AgreementDto.builder()
			.termsId(request.getTermsId())
			.userId(userInfoDto.getUserId())
			.state(request.getState())
			.build();
	}
}
