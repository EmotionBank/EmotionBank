package com.emotionbank.business.domain.notification.service;

import java.util.List;

import com.emotionbank.business.domain.notification.dto.PersonalNotificationDto;
import com.emotionbank.business.domain.notification.dto.PublicNotificationDto;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface NotificationService {

	void reportNotification(PersonalNotificationDto personalNotificationDto) throws FirebaseMessagingException;

	void followNotification(PersonalNotificationDto personalNotificationDto) throws FirebaseMessagingException;

	void sendToTopic(PublicNotificationDto publicNotificationDto, Long userId) throws FirebaseMessagingException;

	List<PersonalNotificationDto> getPersonalNotifications(Long userId);

	List<PublicNotificationDto> getPublicNotifications(Long userId);
}
