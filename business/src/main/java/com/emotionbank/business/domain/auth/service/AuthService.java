package com.emotionbank.business.domain.auth.service;

import com.emotionbank.business.domain.auth.dto.JwtTokens;

public interface AuthService {

	JwtTokens loginOrRegister(String loginType, String code);
}
