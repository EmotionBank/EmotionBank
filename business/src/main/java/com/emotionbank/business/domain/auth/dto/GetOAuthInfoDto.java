package com.emotionbank.business.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetOAuthInfoDto {
	private final String id;

	@Builder
	public GetOAuthInfoDto(String id) {
		this.id = id;
	}

	public static GetOAuthInfoDto from(String id) {
		return GetOAuthInfoDto.builder()
			.id(id)
			.build();
	}
}
