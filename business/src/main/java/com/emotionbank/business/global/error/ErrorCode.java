package com.emotionbank.business.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "businessExceptionTest"),
	// JWT TOKEN
	REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "J-001", "Refresh Token 이 만료되었습니다."),
	REFRESH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "J-002", "유효하지 않은 Refresh Token 입니다."),
	ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "J-003", "Access Token 이 만료되었습니다."),
	ACCESS_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "J-004", "유효하지 않은 Access Token 입니다."),
	// User
	USER_NOT_FOUND(HttpStatus.NOT_FOUND,"U-001","존재하지 않는 회원입니다.");

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String message;
}
