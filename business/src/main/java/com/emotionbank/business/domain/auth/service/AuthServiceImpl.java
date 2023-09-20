package com.emotionbank.business.domain.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.auth.dto.GetOAuthInfoDto;
import com.emotionbank.business.domain.auth.dto.LoginJwtDto;
import com.emotionbank.business.domain.auth.dto.OAuthTokenDto;
import com.emotionbank.business.domain.auth.kakao.client.KakaoTokenClient;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;

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

			return LoginJwtDto.of(user.getRole(), jwtManager.createJwtTokens(savedUser.getUserId()));
		} else {
			User user = optionalUser.get();
			user.updateLastLoginTime(LocalDateTime.now());

			return LoginJwtDto.of(user.getRole(), jwtManager.createJwtTokens(user.getUserId()));
		}
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
