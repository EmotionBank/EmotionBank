package com.emotionbank.business.domain.agreement.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.agreement.constant.State;
import com.emotionbank.business.domain.agreement.dto.AgreementDto;
import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.agreement.repository.AgreementRepository;
import com.emotionbank.business.domain.terms.entity.Terms;
import com.emotionbank.business.domain.terms.repository.TermsRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

	private final AgreementRepository agreementRepository;
	private final TermsRepository termsRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public AgreementDto updateAgreement(AgreementDto agreementDto) {
		User user = userRepository.findById(agreementDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Terms terms = termsRepository.findById(agreementDto.getTermsId())
			.orElseThrow(() -> new BusinessException(ErrorCode.TERMS_NOT_FOUND));
		Agreement agreement = agreementRepository.findAgreementByUserAndTerms(user, terms)
			.orElseThrow(() -> new BusinessException(ErrorCode.AGREEMENT_NOT_FOUND));
		agreement.updateState(State.valueOf(agreementDto.getState()));

		if (agreement.getState() == State.ACTIVE) {
			agreement.updateAgreementTime(LocalDateTime.now());
		}

		return AgreementDto.from(agreement);
	}
}
