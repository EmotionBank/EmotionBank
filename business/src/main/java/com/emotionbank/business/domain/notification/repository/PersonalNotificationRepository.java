package com.emotionbank.business.domain.notification.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emotionbank.business.domain.notification.document.PersonalNotification;

public interface PersonalNotificationRepository extends MongoRepository<PersonalNotification, String> {

	List<PersonalNotification> findByUserId(Long userId);
}
