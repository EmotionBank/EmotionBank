package com.emotionbank.business.global.jwt.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.emotionbank.business.domain.auth.service.JwtManager;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

	private final JwtManager jwtManager;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasAnnotation = parameter.hasParameterAnnotation(UserInfo.class);
		boolean hasDto = UserInfoDto.class.isAssignableFrom(parameter.getParameterType());
		return hasAnnotation && hasDto;
	}

	@Override
	public UserInfoDto resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		String authorizationHeader = request.getHeader("Authorization");
		String accessToken = authorizationHeader.split(" ")[1];
		Claims claims = jwtManager.getTokenClaims(accessToken);
		Long userId = Long.valueOf((Integer)claims.get("userId"));

		return UserInfoDto.from(userId);
	}
}
