package com.emotionbank.business.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthDto {
	private String id;

	@Builder
	public OAuthDto(String id) {
		this.id = id;
	}

	public static OAuthDto from(String id) {
		return OAuthDto.builder()
			.id(id)
			.build();
	}
}
