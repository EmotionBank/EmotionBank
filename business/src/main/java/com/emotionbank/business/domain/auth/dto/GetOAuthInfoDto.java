package com.emotionbank.business.domain.auth.dto;

import com.emotionbank.business.domain.user.constant.SocialType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetOAuthInfoDto {
	private final String id;
	private final SocialType socialType;

	@Builder
	public GetOAuthInfoDto(String id, SocialType socialType) {
		this.id = id;
		this.socialType = socialType;
	}

	public static GetOAuthInfoDto from(String id, SocialType socialType) {
		return GetOAuthInfoDto.builder()
			.id(id)
			.socialType(socialType)
			.build();
	}
}
