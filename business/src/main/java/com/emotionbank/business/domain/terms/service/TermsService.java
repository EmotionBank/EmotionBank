package com.emotionbank.business.domain.terms.service;

import java.util.List;

import com.emotionbank.business.domain.terms.dto.TermsDto;

public interface TermsService {
	TermsDto createTerms(TermsDto termsDto);

	List<TermsDto> getTerms();
}
