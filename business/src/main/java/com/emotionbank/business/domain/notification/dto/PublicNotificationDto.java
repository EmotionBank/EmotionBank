package com.emotionbank.business.domain.notification.dto;

import java.time.LocalDateTime;

import com.emotionbank.business.api.notification.dto.PublicNotificationApiDto;
import com.emotionbank.business.domain.notification.document.PublicNotification;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PublicNotificationDto {
	private String title;
	private String body;
	private LocalDateTime createTime;

	@Builder
	public PublicNotificationDto(String title, String body, LocalDateTime createTime) {
		this.title = title;
		this.body = body;
		this.createTime = createTime;
	}

	public static PublicNotificationDto from(PublicNotificationApiDto.Request request) {
		return com.emotionbank.business.domain.notification.dto.PublicNotificationDto.builder()
			.title(request.getTitle())
			.body(request.getBody())
			.build();
	}

	public static PublicNotificationDto from(PublicNotification publicNotification){
		return com.emotionbank.business.domain.notification.dto.PublicNotificationDto.builder()
			.title(publicNotification.getTitle())
			.body(publicNotification.getBody())
			.createTime(publicNotification.getCreateTime())
			.build();
	}
}
