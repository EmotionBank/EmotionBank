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
	JWT_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "J-005", "유효하지 않은 JWT 입니다."),

	// User
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U-001", "존재하지 않는 회원입니다."),
	NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "U-002", "중복된 닉네임 입니다."),
	USER_ALREADY_SIGNUP(HttpStatus.CONFLICT, "U-003", "이미 가입된 회원입니다."),

	// Category
	CATEGORY_NOT_EXIST(HttpStatus.NOT_FOUND, "C-001", "카테고리가 없습니다."),

	// Account
	SENDER_ACCOUNT_NOT_EXIST(HttpStatus.NOT_FOUND, "A-001", "Sender 계좌가 없습니다"),
	RECEIVER_ACCOUNT_NOT_EXIST(HttpStatus.NOT_FOUND, "A-002", "Receiver 계좌가 없습니다"),
	ACCOUNT_NOT_EXIST(HttpStatus.NOT_FOUND, "A-003", "존재하지 않는 계좌 번호입니다."),
	BALANCE_NOT_EQUAL(HttpStatus.BAD_REQUEST, "A-004", "잔액이 일치하지 않습니다."),
	BELOW_ZERO_BALANCE(HttpStatus.BAD_REQUEST, "A-005", "출금이 불가합니다."),

	// Calendar
	CALENDAR_NOT_EXIST(HttpStatus.NOT_FOUND, "L-001", "해당 일자에 기록이 존재하지 않습니다."),

	// OAuth
	NOT_SUPPORT_LOGIN_EXCEPTION(HttpStatus.BAD_REQUEST, "O-001", "지원하지 않는 로그인 방식입니다."),
	NOT_EXIST_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "O-002", "Authorization Header가 빈값입니다."),
	NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "O-003", "인증 타입이 Bearer 타입이 아닙니다."),
	NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "O-004", "Access Token이 아닙니다."),

	// Transaction
	TRANSACTION_NOT_EXIST(HttpStatus.NOT_FOUND, "T-001", "일치하는 거래가 없습니다."),
	TRANSACTION_BAD_REQUEST(HttpStatus.BAD_REQUEST, "T-002", "비공개 거래 내역은 볼 수 없습니다.");

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String message;
}
