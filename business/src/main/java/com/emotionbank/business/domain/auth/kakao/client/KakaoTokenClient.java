package com.emotionbank.business.domain.auth.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotionbank.business.domain.auth.dto.OAuthTokenDto;

@FeignClient(url = "https://kauth.kakao.com", contextId = "accessToken", name = "kakaoTokenClient")
public interface KakaoTokenClient {

	@PostMapping(value = "/oauth/token", consumes = "application/json")
	OAuthTokenDto.Response requestKakaoToken(@RequestHeader("Content-type") String contentType,
		@RequestParam("grant_type") String grantType,
		@RequestParam("client_id") String clientId,
		@RequestParam("client_secret") String clientSecret,
		@RequestParam("redirect_uri") String redirectUri,
		@RequestParam("code") String code);
}
