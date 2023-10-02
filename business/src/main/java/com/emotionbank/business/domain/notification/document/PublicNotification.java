package com.emotionbank.business.domain.notification.document;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.emotionbank.business.domain.notification.dto.PublicNotificationDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document("public-notification")
public class PublicNotification {
	private String title;
	private String body;
	private LocalDateTime createTime;

	@Builder
	public PublicNotification(String title, String body, LocalDateTime createTime) {
		this.title = title;
		this.body = body;
		this.createTime = createTime;
	}

	public static PublicNotification from(PublicNotificationDto publicNotificationDto) {
		return PublicNotification.builder()
			.title(publicNotificationDto.getTitle())
			.body(publicNotificationDto.getBody())
			.createTime(LocalDateTime.now())
			.build();
	}
}
