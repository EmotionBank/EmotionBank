package com.emotionbank.business.domain.terms.dto;

import com.emotionbank.business.api.terms.dto.TermsCreateDto;
import com.emotionbank.business.domain.terms.constant.Mandatory;
import com.emotionbank.business.domain.terms.entity.Terms;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsDto {
	private Long termsId;
	private String title;
	private String content;
	private Mandatory mandatory;

	@Builder
	public TermsDto(Long termsId, String title, String content, Mandatory mandatory) {
		this.termsId = termsId;
		this.title = title;
		this.content = content;
		this.mandatory = mandatory;
	}

	public static TermsDto from(Terms terms) {
		return TermsDto.builder()
			.termsId(terms.getTermsId())
			.title(terms.getTitle())
			.content(terms.getContent())
			.mandatory(terms.getMandatory())
			.build();
	}

	public static TermsDto from(TermsCreateDto.Request request) {
		return TermsDto.builder()
			.title(request.getTitle())
			.content(request.getContent())
			.mandatory(Mandatory.valueOf(request.getMandatory()))
			.build();
	}
}
