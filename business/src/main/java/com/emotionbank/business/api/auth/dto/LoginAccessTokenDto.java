package com.emotionbank.business.api.auth.dto;

import com.emotionbank.business.domain.user.constant.Role;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginAccessTokenDto {
	@JsonUnwrapped
	private AccessTokenDto accessTokenDto;
	private Role role;

	@Builder
	public LoginAccessTokenDto(AccessTokenDto accessTokenDto, Role role) {
		this.accessTokenDto = accessTokenDto;
		this.role = role;
	}

	public static LoginAccessTokenDto of(AccessTokenDto accessTokenDto, Role role) {
		return LoginAccessTokenDto.builder()
			.accessTokenDto(accessTokenDto)
			.role(role)
			.build();
	}
}
