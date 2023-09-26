package com.emotionbank.business.domain.terms.service;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.terms.dto.TermsDto;
import com.emotionbank.business.domain.terms.entity.Terms;
import com.emotionbank.business.domain.terms.repository.TermsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

	private final TermsRepository termsRepository;

	@Override
	public TermsDto createTerms(TermsDto termsDto) {
		Terms terms = termsRepository.save(Terms.from(termsDto));
		return TermsDto.from(terms);
	}
}
