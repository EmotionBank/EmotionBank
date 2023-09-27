package com.emotionbank.business.domain.auth.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.agreement.repository.AgreementRepository;
import com.emotionbank.business.domain.auth.dto.AccessTokenDto;
import com.emotionbank.business.domain.auth.dto.GetOAuthInfoDto;
import com.emotionbank.business.domain.auth.dto.LoginJwtDto;
import com.emotionbank.business.domain.auth.dto.OAuthTokenDto;
import com.emotionbank.business.domain.auth.entity.RefreshToken;
import com.emotionbank.business.domain.auth.kakao.client.KakaoTokenClient;
import com.emotionbank.business.domain.auth.repository.RefreshTokenRepository;
import com.emotionbank.business.domain.terms.repository.TermsRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.AuthException;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final KakaoTokenClient kakaoTokenClient;
	private final SocialLoginServices socialLoginServices;
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	private final RefreshTokenRepository refreshTokenRepository;
	private final AgreementRepository agreementRepository;
	private final TermsRepository termsRepository;

	@Value("${kakao.client.id}")
	private String kakaoClientId;

	@Value("${kakao.client.secret}")
	private String kakaoClientSecret;

	@Value("${social.context-type}")
	private String contextType;

	@Value("${redirect-uri}")
	private String redirectUri;

	@Override
	@Transactional
	public LoginJwtDto loginOrRegister(String loginType, String code) {
		OAuthTokenDto.Request oAuthRequestDto = OAuthTokenDto.Request.of(kakaoClientId, kakaoClientSecret,
			redirectUri, code);
		OAuthTokenDto.Response oAuthToken = requestKakaoToken(oAuthRequestDto);

		SocialLoginService socialLoginService = socialLoginServices.mapping(loginType);
		GetOAuthInfoDto memberInfo = socialLoginService.getMemberInfo("Bearer " + oAuthToken.getAccessToken());

		Optional<User> optionalUser = userRepository.findBySocialIdAndSocialType(memberInfo.getId(),
			memberInfo.getSocialType());

		if (optionalUser.isEmpty()) {
			User user = User.of(memberInfo.getId(), memberInfo.getSocialType());
			user.updateLastLoginTime(LocalDateTime.now());

			User savedUser = userRepository.save(user);

			List<Agreement> agreements = termsRepository.findAll()
				.stream()
				.map(terms -> agreementRepository.save(Agreement.newSignUpAgreement(savedUser, terms)))
				.collect(Collectors.toList());

			return LoginJwtDto.of(user.getRole(), jwtManager.createJwtTokens(savedUser.getUserId()));
		} else {
			User user = optionalUser.get();
			user.updateLastLoginTime(LocalDateTime.now());

			return LoginJwtDto.of(user.getRole(), jwtManager.createJwtTokens(user.getUserId()));
		}
	}

	@Override
	public AccessTokenDto renewalAccessToken(String refreshToken, String authorizationHeader) {
		String accessToken = authorizationHeader.split(" ")[1];
		if (jwtManager.validateRefreshTokenAndExpiredAccessToken(refreshToken, accessToken)) {
			Claims refreshClaims = jwtManager.getTokenClaims(refreshToken);
			Long userId = Long.valueOf((Integer)refreshClaims.get("userId"));

			Optional<RefreshToken> savedRefreshToken = refreshTokenRepository.findRefreshTokenByUserId(userId);

			if (isNotSavedRefreshToken(refreshToken, savedRefreshToken)) {
				throw new AuthException(ErrorCode.REFRESH_TOKEN_INVALID);
			}

			if (!userRepository.existsById(userId)) {
				throw new AuthException(ErrorCode.REFRESH_TOKEN_INVALID);
			}

			String newAccessToken = jwtManager.createAccessToken(userId, jwtManager.createAccessTokenExpireTime());
			return AccessTokenDto.createBearer(newAccessToken);
		}
		throw new AuthException(ErrorCode.REFRESH_TOKEN_INVALID);
	}

	private static boolean isNotSavedRefreshToken(String refreshToken, Optional<RefreshToken> savedRefreshToken) {
		return savedRefreshToken.isEmpty() || !savedRefreshToken.get().getRefreshToken().equals(refreshToken);
	}

	private OAuthTokenDto.Response requestKakaoToken(OAuthTokenDto.Request oAuthRequestDto) {
		return kakaoTokenClient.requestKakaoToken(
			contextType,
			oAuthRequestDto.getGrantType(),
			oAuthRequestDto.getClientId(),
			oAuthRequestDto.getClientSecret(),
			oAuthRequestDto.getRedirectUri(),
			oAuthRequestDto.getCode());
	}
}
