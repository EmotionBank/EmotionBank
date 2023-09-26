package com.emotionbank.business.domain.terms.service;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<TermsDto> getTerms() {
		List<Terms> terms = termsRepository.findAll();

		return terms.stream().map(TermsDto::from).collect(Collectors.toList());
	}
}
