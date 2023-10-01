package com.emotionbank.business.domain.notification.constant;

import lombok.Getter;

@Getter
public enum NotificationType {
	FOLLOW("팔로우"), REPORT("감정 리포트")
	;

	NotificationType(String title) {
		this.title = title;
	}

	private final String title;
}
