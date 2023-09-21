package com.emotionbank.business.domain.auth.entity;

import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
	@Id
	private Long userId;
	private String refreshToken;

	public static RefreshToken of(Long userId, String refreshToken) {
		return RefreshToken.builder()
			.userId(userId)
			.refreshToken(refreshToken)
			.build();
	}

}
