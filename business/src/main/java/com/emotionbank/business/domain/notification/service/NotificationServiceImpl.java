package com.emotionbank.business.domain.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.fcmtoken.entity.FcmToken;
import com.emotionbank.business.domain.fcmtoken.repository.FcmTokenRepository;
import com.emotionbank.business.domain.notification.document.PersonalNotification;
import com.emotionbank.business.domain.notification.document.PublicNotification;
import com.emotionbank.business.domain.notification.dto.PersonalNotificationDto;
import com.emotionbank.business.domain.notification.dto.PublicNotificationDto;
import com.emotionbank.business.domain.notification.repository.PersonalNotificationRepository;
import com.emotionbank.business.domain.notification.repository.PublicNotificationRepository;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final UserRepository userRepository;
	private final FcmTokenRepository tokenRepository;
	private final FirebaseMessaging firebaseMessaging;
	private final PublicNotificationRepository publicNotificationRepository;
	private final PersonalNotificationRepository personalNotificationRepository;

	@Override
	public void reportNotification(PersonalNotificationDto personalNotificationDto) throws FirebaseMessagingException {
		User user = userRepository.findById(personalNotificationDto.getUserId())
			.orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
		List<FcmToken> userTokens = tokenRepository.findByUser(user);
		Notification notification = Notification.builder()
			.setTitle(personalNotificationDto.getNotificationType().getTitle())
			.setBody(user.getNickname() + "님의 감정 리포트가 도착했습니다.")
			.build();
		for(FcmToken token:userTokens) {
			firebaseMessaging.send(Message.builder()
				.setNotification(notification)
				.setToken(token.getToken())
				.build());
		}
		personalNotificationRepository.save(PersonalNotification.from(personalNotificationDto));
	}

	@Override
	public void followNotification(PersonalNotificationDto personalNotificationDto) throws FirebaseMessagingException {
		User follower = userRepository.findById(personalNotificationDto.getFollowerId())
			.orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
		User user = userRepository.findById(personalNotificationDto.getUserId())
			.orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
		List<FcmToken> userTokens = tokenRepository.findByUser(user);
		Notification notification = Notification.builder()
			.setTitle(personalNotificationDto.getNotificationType().getTitle())
			.setBody(follower.getNickname() + "님이 회원님을 팔로우하기 시작했습니다.")
			.build();
		for(FcmToken token:userTokens) {
			firebaseMessaging.send(Message.builder()
				.setNotification(notification)
				.setToken(token.getToken())
				.build());
		}
		personalNotificationRepository.save(PersonalNotification.of(personalNotificationDto, follower.getNickname()));
	}

	@Override
	public void sendToTopic(PublicNotificationDto publicNotificationDto, Long userId) throws FirebaseMessagingException {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		if (user.getRole() != Role.ADMIN){
			throw new BusinessException(ErrorCode.NOTIFICATION_CREATE_UNAUTHORIZED);
		}
		Notification notification = Notification.builder()
			.setTitle(publicNotificationDto.getTitle())
			.setBody(publicNotificationDto.getBody())
			.build();
		firebaseMessaging.send(Message.builder()
			.setNotification(notification)
			.setTopic("notice")
			.build());
		publicNotificationRepository.save(PublicNotification.from(publicNotificationDto));
	}

	@Override
	public List<PersonalNotificationDto> getPersonalNotifications(Long userId) {
		userRepository.findById(userId)
			.orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
		return personalNotificationRepository.findByUserId(userId).stream()
			.map(PersonalNotificationDto::from)
			.collect(Collectors.toList());
	}

	@Override
	public List<PublicNotificationDto> getPublicNotifications(Long userId) {
		userRepository.findById(userId)
			.orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
		return publicNotificationRepository.findByCreateTimeAfter(LocalDateTime.now().minusDays(30))
			.stream()
			.map(PublicNotificationDto::from)
			.collect(Collectors.toList());
	}
}
