package com.emotionbank.business.domain.agreement.service;

import java.util.List;

import com.emotionbank.business.domain.agreement.dto.AgreementDto;

public interface AgreementService {
	AgreementDto updateAgreement(AgreementDto agreementDto);

	List<AgreementDto> getAgreementList(Long userId);
}
