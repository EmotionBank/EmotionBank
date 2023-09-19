package com.emotionbank.business.domain.auth.kakao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.auth.dto.GetOAuthInfoDto;
import com.emotionbank.business.domain.auth.kakao.client.KakaoInfoClient;
import com.emotionbank.business.domain.auth.kakao.dto.KakaoInfoResponseDto;
import com.emotionbank.business.domain.auth.service.SocialLoginService;
import com.emotionbank.business.domain.user.constant.SocialType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoSocialLoginServiceImpl implements SocialLoginService {
	private static final String PROVIDER = "kakao";
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";

	private final KakaoInfoClient kakaoInfoClient;

	@Override
	public boolean is(final String serviceName) {
		return PROVIDER.equals(serviceName);
	}

	@Override
	public GetOAuthInfoDto getMemberInfo(String token) {
		KakaoInfoResponseDto kakaoInfoResponseDto = kakaoInfoClient.getKakaoMemberInfo(CONTENT_TYPE, token);
		return GetOAuthInfoDto.from(kakaoInfoResponseDto.getKakaoId(), SocialType.KAKAO);
	}
}
