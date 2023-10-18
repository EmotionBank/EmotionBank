package com.emotionbank.business.domain.notification.document;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.emotionbank.business.domain.notification.constant.NotificationType;
import com.emotionbank.business.domain.notification.dto.PersonalNotificationDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document("personal-notification")
public class PersonalNotification {

	private Long userId;

	private Long followerId;

	private String followerNickname;

	private NotificationType notificationType;

	private LocalDateTime createTime;

	@Builder
	public PersonalNotification(Long userId, Long followerId, String followerNickname,
		NotificationType notificationType,
		LocalDateTime createTime) {
		this.userId = userId;
		this.followerId = followerId;
		this.followerNickname = followerNickname;
		this.notificationType = notificationType;
		this.createTime = createTime;
	}

	public static PersonalNotification from(PersonalNotificationDto personalNotificationDto) {
		return PersonalNotification.builder()
			.userId(personalNotificationDto.getUserId())
			.notificationType(personalNotificationDto.getNotificationType())
			.createTime((LocalDateTime.now().plusHours(9L)))
			.build();
	}

	public static PersonalNotification of(PersonalNotificationDto personalNotificationDto, String follwerNickname) {
		return PersonalNotification.builder()
			.userId(personalNotificationDto.getUserId())
			.followerId(personalNotificationDto.getFollowerId())
			.followerNickname(follwerNickname)
			.notificationType(personalNotificationDto.getNotificationType())
			.createTime(LocalDateTime.now().plusHours(9L))
			.build();
	}
}
