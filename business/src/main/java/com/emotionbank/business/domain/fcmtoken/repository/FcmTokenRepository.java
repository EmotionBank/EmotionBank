package com.emotionbank.business.domain.fcmtoken.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emotionbank.business.domain.fcmtoken.entity.FcmToken;
import com.emotionbank.business.domain.user.entity.User;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {

	List<FcmToken> findByUser(User user);

	Optional<FcmToken> findByUserAndToken(User user, String token);
}
