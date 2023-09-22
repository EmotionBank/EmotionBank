package com.emotionbank.business.domain.auth.service;

import com.emotionbank.business.domain.auth.dto.AccessTokenDto;
import com.emotionbank.business.domain.auth.dto.LoginJwtDto;

public interface AuthService {

	LoginJwtDto loginOrRegister(String loginType, String code);

	AccessTokenDto renewalAccessToken(String refreshToken, String authorizationHeader);
}
