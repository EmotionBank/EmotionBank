package com.emotionbank.business.api.notification.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.notification.constant.NotificationType;
import com.emotionbank.business.domain.notification.dto.PersonalNotificationDto;

import lombok.Builder;
import lombok.Getter;

public class PersonalNotificationApiDto {

	@Getter
	public static class PersonalNotificationInfo {
		private Long followerId;

		private String followerNickname;

		private String body;

		private NotificationType notificationType;

		private LocalDateTime createTime;

		@Builder
		public PersonalNotificationInfo(Long followerId, String followerNickname, String body,
			NotificationType notificationType, LocalDateTime createTime) {
			this.followerId = followerId;
			this.followerNickname = followerNickname;
			this.body = body;
			this.notificationType = notificationType;
			this.createTime = createTime;
		}

		public static PersonalNotificationInfo from(PersonalNotificationDto personalNotificationDto){
			if (personalNotificationDto.getNotificationType().equals(NotificationType.FOLLOW)) {
				return PersonalNotificationInfo.builder()
					.followerId(personalNotificationDto.getFollowerId())
					.followerNickname(personalNotificationDto.getFollowerNickname())
					.body(personalNotificationDto.getFollowerNickname() + "님이 회원님을 팔로우하기 시작했습니다.")
					.notificationType(personalNotificationDto.getNotificationType())
					.createTime(personalNotificationDto.getCreateTime())
					.build();
			}
			return PersonalNotificationInfo.builder()
				.followerId(personalNotificationDto.getFollowerId())
				.followerNickname(personalNotificationDto.getFollowerNickname())
				.body("감정 리포트가 도착했습니다.")
				.notificationType(personalNotificationDto.getNotificationType())
				.createTime(personalNotificationDto.getCreateTime())
				.build();
		}
	}

	@Getter
	public static class Response {
		List<PersonalNotificationInfo> personalNotificationList;

		@Builder
		public Response(List<PersonalNotificationInfo> personalNotificationList) {
			this.personalNotificationList = personalNotificationList;
		}

		public static Response from(List<PersonalNotificationDto> personalNotificationDtoList){
			return Response.builder()
				.personalNotificationList(personalNotificationDtoList.stream()
					.map(PersonalNotificationInfo::from)
					.collect(Collectors.toList()))
				.build();
		}
	}
}
