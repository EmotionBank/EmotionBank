package com.emotionbank.business.global.component;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.emotionbank.business.domain.agreement.constant.State;
import com.emotionbank.business.domain.agreement.dto.AgreementDto;
import com.emotionbank.business.domain.agreement.service.AgreementService;
import com.emotionbank.business.domain.auth.service.JwtManager;
import com.emotionbank.business.domain.terms.constant.Mandatory;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgreementInterceptor implements HandlerInterceptor {
	private static final String authorization = "Authorization";
	private final JwtManager jwtManager;
	private final AgreementService agreementService;

	@SuppressWarnings("checkstyle:OperatorWrap")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
			return true;
		}

		String authorizationHeader = request.getHeader(authorization);

		String token = authorizationHeader.split(" ")[1];

		Claims claims = jwtManager.getTokenClaims(token);
		Long userId = Long.valueOf((Integer)claims.get("userId"));

		List<AgreementDto> agreementList = agreementService.getAgreementList(userId);

		Long agreementsSize = agreementList
			.stream()
			.filter(agreementDto ->
				agreementDto.getMandatory().equals(Mandatory.ESSENTIAL.name())
					&& !agreementDto.getState().equals(State.ACTIVE.name())
			).count();

		if (!agreementsSize.equals(0L)) {
			throw new BusinessException(ErrorCode.TERMS_NOT_ACTIVE);
		}

		return true;
	}
}
