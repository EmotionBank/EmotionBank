package com.emotionbank.business.domain.auth.service;

import com.emotionbank.business.domain.auth.dto.AccessTokenDto;
import com.emotionbank.business.domain.auth.dto.LoginJwtDto;
import com.emotionbank.business.domain.auth.dto.SignUpDto;
import com.emotionbank.business.domain.auth.dto.SignUpUserDto;

public interface AuthService {

	LoginJwtDto loginOrRegister(String loginType, String code);

	AccessTokenDto renewalAccessToken(String refreshToken, String authorizationHeader);

	SignUpUserDto signup(SignUpDto signUpDto);

	void removeRefreshToken(Long userInfoDto);

	void addBlackList(String token);
}
