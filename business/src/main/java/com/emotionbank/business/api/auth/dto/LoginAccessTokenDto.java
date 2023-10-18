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
	private RenewalAccessTokenDto renewalAccessTokenDto;
	private Role role;

	@Builder
	public LoginAccessTokenDto(RenewalAccessTokenDto renewalAccessTokenDto, Role role) {
		this.renewalAccessTokenDto = renewalAccessTokenDto;
		this.role = role;
	}

	public static LoginAccessTokenDto of(RenewalAccessTokenDto renewalAccessTokenDto, Role role) {
		return LoginAccessTokenDto.builder()
			.renewalAccessTokenDto(renewalAccessTokenDto)
			.role(role)
			.build();
	}
}
