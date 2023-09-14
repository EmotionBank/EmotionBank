package com.emotionbank.business.global.error.exception;

import com.emotionbank.business.global.error.ErrorCode;

public class JwtTokenException extends BusinessException {
	public JwtTokenException(ErrorCode errorCode) {
		super(errorCode);
	}
}
