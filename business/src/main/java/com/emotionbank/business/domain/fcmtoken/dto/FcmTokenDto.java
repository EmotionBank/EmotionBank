package com.emotionbank.business.domain.fcmtoken.dto;

import java.time.LocalDateTime;

import com.emotionbank.business.api.fcmtoken.dto.RegisterTokenDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FcmTokenDto {
	private Long tokenId;
	private Long userId;
	private String token;
	private LocalDateTime createTime;

	@Builder
	public FcmTokenDto(Long tokenId, Long userId, String token, LocalDateTime createTime) {
		this.tokenId = tokenId;
		this.userId = userId;
		this.token = token;
		this.createTime = createTime;
	}

	public static FcmTokenDto of(RegisterTokenDto.Request request, Long userId) {
		return FcmTokenDto.builder()
			.userId(userId)
			.token(request.getToken())
			.build();
	}
}
