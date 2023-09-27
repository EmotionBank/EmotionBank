package com.emotionbank.business.domain.notification.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emotionbank.business.domain.notification.document.PublicNotification;

public interface PublicNotificationRepository extends MongoRepository<PublicNotification, String> {

	List<PublicNotification> findByCreateTimeAfter(LocalDateTime date);
}
