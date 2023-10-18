package com.emotionbank.business.api.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.notification.dto.PersonalNotificationApiDto;
import com.emotionbank.business.api.notification.dto.PublicNotificationApiDto;
import com.emotionbank.business.domain.notification.dto.PublicNotificationDto;
import com.emotionbank.business.domain.notification.service.NotificationService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;
import com.google.firebase.messaging.FirebaseMessagingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;

	@PostMapping
	public ResponseEntity<Void> makePublicNotification(@RequestBody PublicNotificationApiDto.Request request,
		@UserInfo UserInfoDto userInfoDto) throws
		FirebaseMessagingException {
		notificationService.sendToTopic(PublicNotificationDto.from(request), userInfoDto.getUserId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/public")
	public ResponseEntity<PublicNotificationApiDto.Response> getPublicNotifications(@UserInfo UserInfoDto userInfoDto) {
		PublicNotificationApiDto.Response response = PublicNotificationApiDto.Response
			.from(notificationService.getPublicNotifications(userInfoDto.getUserId()));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/me")
	public ResponseEntity<PersonalNotificationApiDto.Response> getPersonalNotification(
		@UserInfo UserInfoDto userInfoDto) {
		PersonalNotificationApiDto.Response response = PersonalNotificationApiDto.Response
			.from(notificationService.getPersonalNotifications(
				userInfoDto.getUserId()));
		return ResponseEntity.ok(response);
	}
}
