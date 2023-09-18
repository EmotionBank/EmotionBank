package com.emotionbank.business.domain.auth.service;

import com.emotionbank.business.domain.auth.dto.OAuthDto;

public interface SocialLoginService {

	boolean is(final String serviceName);

	OAuthDto getMemberInfo(final String code);
}