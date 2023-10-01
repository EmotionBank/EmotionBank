package com.emotionbank.business.domain.notification.dto;

import java.time.LocalDateTime;

import com.emotionbank.business.domain.notification.constant.NotificationType;
import com.emotionbank.business.domain.notification.document.PersonalNotification;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PersonalNotificationDto {
	private Long userId;

	private Long followerId;

	private String followerNickname;

	private NotificationType notificationType;

	private LocalDateTime createTime;

	@Builder
	public PersonalNotificationDto(Long userId, Long followerId, String followerNickname,
		NotificationType notificationType,
		LocalDateTime createTime) {
		this.userId = userId;
		this.followerId = followerId;
		this.followerNickname = followerNickname;
		this.notificationType = notificationType;
		this.createTime = createTime;
	}

	public static PersonalNotificationDto from(PersonalNotification personalNotification){
		return PersonalNotificationDto.builder()
			.userId(personalNotification.getUserId())
			.followerId(personalNotification.getFollowerId())
			.followerNickname(personalNotification.getFollowerNickname())
			.notificationType(personalNotification.getNotificationType())
			.createTime(personalNotification.getCreateTime())
			.build();
	}


}
