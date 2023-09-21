package com.emotionbank.business.global.jwt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.emotionbank.business.domain.auth.constant.TokenType;
import com.emotionbank.business.domain.auth.service.JwtManager;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.AuthException;
import com.emotionbank.business.global.jwt.util.AuthorizationHeaderUtils;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	private final String authorization = "Authorization";
	private final JwtManager jwtManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
			return true;
		}

		String authorizationHeader = request.getHeader(authorization);
		AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

		String token = authorizationHeader.split(" ")[1];
		jwtManager.validateAccessToken(token);

		Claims claims = jwtManager.getAccessTokenClaims(token);
		String type = claims.getSubject();

		if (!TokenType.isAccessToken(type)) {
			throw new AuthException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
		}

		return true;
	}

}
