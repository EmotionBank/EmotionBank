package com.emotionbank.business.global.jwt.util;

import org.springframework.util.StringUtils;

import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.AuthException;

public class AuthorizationHeaderUtils {

	private static final String bearer = "Bearer";

	public static void validateAuthorization(String authorizationHeader) {
		if (!StringUtils.hasText(authorizationHeader)) {
			throw new AuthException(ErrorCode.NOT_EXIST_AUTHORIZATION);
		}
		String[] authorizations = authorizationHeader.split(" ");
		if (authorizationTypeCheck(authorizations)) {
			throw new AuthException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
		}
	}

	private static boolean authorizationTypeCheck(String[] authorizations) {
		return authorizations.length < 2 || !bearer.equals(authorizations[0]);
	}

}
