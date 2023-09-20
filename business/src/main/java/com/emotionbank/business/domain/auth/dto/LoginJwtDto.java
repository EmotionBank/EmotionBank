package com.emotionbank.business.domain.auth.dto;

import com.emotionbank.business.domain.user.constant.Role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginJwtDto {
	Role role;
	JwtTokens jwtTokens;

	@Builder
	public LoginJwtDto(Role role, JwtTokens jwtTokens) {
		this.role = role;
		this.jwtTokens = jwtTokens;
	}

	public static LoginJwtDto of(Role role, JwtTokens jwtTokens) {
		return LoginJwtDto.builder()
			.role(role)
			.jwtTokens(jwtTokens)
			.build();
	}
}
