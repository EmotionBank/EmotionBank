package com.emotionbank.business.domain.fcmtoken.service;

import java.util.Collections;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.fcmtoken.dto.FcmTokenDto;
import com.emotionbank.business.domain.fcmtoken.entity.FcmToken;
import com.emotionbank.business.domain.fcmtoken.repository.FcmTokenRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FcmTokenServiceImpl implements FcmTokenService {

	private final FcmTokenRepository tokenRepository;
	private final UserRepository userRepository;
	private final FirebaseMessaging firebaseMessaging;

	@Override
	public void createToken(FcmTokenDto tokenDto) throws FirebaseMessagingException {
		User user = userRepository.findById(tokenDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		firebaseMessaging.subscribeToTopic(Collections.singletonList(tokenDto.getToken()), "notice");
		if (tokenRepository.findByUserAndToken(user, tokenDto.getToken()).isEmpty()){
			tokenRepository.save(FcmToken.of(user, tokenDto.getToken()));
		}
	}

}
