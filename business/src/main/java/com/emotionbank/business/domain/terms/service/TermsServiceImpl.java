package com.emotionbank.business.domain.terms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.agreement.repository.AgreementRepository;
import com.emotionbank.business.domain.terms.dto.TermsDto;
import com.emotionbank.business.domain.terms.entity.Terms;
import com.emotionbank.business.domain.terms.repository.TermsRepository;
import com.emotionbank.business.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

	private final TermsRepository termsRepository;
	private final UserRepository userRepository;
	private final AgreementRepository agreementRepository;

	@Override
	@Transactional
	public TermsDto createTerms(TermsDto termsDto) {
		Terms terms = termsRepository.save(Terms.from(termsDto));

		List<Agreement> agreements = userRepository.findAll().stream()
			.map(user -> agreementRepository.save(Agreement.newSignUpAgreement(user, terms)))
			.collect(Collectors.toList());

		return TermsDto.from(terms);
	}
}
