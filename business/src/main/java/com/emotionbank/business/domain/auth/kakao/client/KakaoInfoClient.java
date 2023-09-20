package com.emotionbank.business.domain.auth.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.emotionbank.business.domain.auth.kakao.dto.KakaoInfoResponseDto;

@FeignClient(url = "https://kapi.kakao.com", contextId = "kakaoInfo", name = "kakaoInfoClient")
public interface KakaoInfoClient {
	@GetMapping(value = "/v2/user/me", consumes = "application/json")
	KakaoInfoResponseDto getKakaoMemberInfo(@RequestHeader("Content-type") String contentType,
		@RequestHeader("Authorization") String accessToken);
}
