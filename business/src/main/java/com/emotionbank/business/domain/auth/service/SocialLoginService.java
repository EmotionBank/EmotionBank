package com.emotionbank.business.domain.auth.service;

import com.emotionbank.business.domain.auth.dto.GetOAuthInfoDto;

public interface SocialLoginService {

	boolean is(final String serviceName);

	GetOAuthInfoDto getMemberInfo(final String code);
}
