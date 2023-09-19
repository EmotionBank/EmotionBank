package com.emotionbank.business.global.error.exception;

import com.emotionbank.business.global.error.ErrorCode;

public class AuthException extends BusinessException {
	public AuthException(ErrorCode errorCode) {
		super(errorCode);
	}
}
