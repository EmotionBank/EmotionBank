package com.emotionbank.business.domain.auth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class KakaoInfoResponseDto {
	@JsonProperty("id")
	private String kakaoId;
}
