package com.emotionbank.business.api.notification.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.notification.dto.PublicNotificationDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PublicNotificationApiDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String title;
		private String body;

		@Builder
		public Request(String title, String body) {
			this.title = title;
			this.body = body;
		}
	}

	@Getter
	public static class PublicNotificationInfo {
		private String title;
		private String body;
		private LocalDateTime createTime;

		@Builder
		public PublicNotificationInfo(String title, String body, LocalDateTime createTime) {
			this.title = title;
			this.body = body;
			this.createTime = createTime;
		}

		public static PublicNotificationInfo from(PublicNotificationDto publicNotificationDto) {
			return PublicNotificationInfo.builder()
				.title(publicNotificationDto.getTitle())
				.body(publicNotificationDto.getBody())
				.createTime(publicNotificationDto.getCreateTime())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {

		private List<PublicNotificationInfo> publicNotificationList;

		@Builder
		public Response(List<PublicNotificationInfo> publicNotificationList) {
			this.publicNotificationList = publicNotificationList;
		}

		public static Response from(List<PublicNotificationDto> publicNotificationDtoList) {
			return Response.builder()
				.publicNotificationList(publicNotificationDtoList.stream()
					.map(PublicNotificationInfo::from)
					.collect(Collectors.toList()))
				.build();
		}
	}




}
