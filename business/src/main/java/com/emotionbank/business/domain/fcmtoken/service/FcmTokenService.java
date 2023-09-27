package com.emotionbank.business.domain.fcmtoken.service;

import com.emotionbank.business.domain.fcmtoken.dto.FcmTokenDto;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface FcmTokenService {
	void createToken(FcmTokenDto tokenDto) throws FirebaseMessagingException;
}
