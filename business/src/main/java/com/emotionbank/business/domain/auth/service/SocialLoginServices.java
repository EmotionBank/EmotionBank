package com.emotionbank.business.domain.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.AuthException;

@Service
public class SocialLoginServices {
	private final List<SocialLoginService> socialLoginServices;

	public SocialLoginServices(final List<SocialLoginService> socialLoginServices) {
		this.socialLoginServices = socialLoginServices;
	}

	public SocialLoginService mapping(final String serviceName) {
		return socialLoginServices.stream()
			.filter(socialLoginService -> socialLoginService.is(serviceName))
			.findFirst()
			.orElseThrow(() -> new AuthException(ErrorCode.NOT_SUPPORT_LOGIN_EXCEPTION));
	}
}
