package com.emotionbank.business.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "businessExceptionTest"),

	ACCOUNT_NOT_EXIST(HttpStatus.BAD_GATEWAY,"A-001","계좌가 없습니다")
	;

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	private HttpStatus httpStatus;
	private String errorCode;
	private String message;
}
