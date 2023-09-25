package com.emotionbank.business.domain.signup.service;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.signup.dto.SignUpDto;
import com.emotionbank.business.domain.signup.dto.SignUpUserDto;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpImplService implements SignUpService {
	private final UserRepository userRepository;

	@Override
	public SignUpUserDto signup(SignUpDto signUpDto) {
		User user = userRepository.findById(signUpDto.getUserId()).orElseThrow(()
			-> new BusinessException(ErrorCode.USER_NOT_FOUND));

		if (user.getRole() != Role.PENDING) {
			throw new BusinessException(ErrorCode.USER_ALREADY_SIGNUP);
		}

		user.updateNickname(signUpDto.getNickname());
		user.updateBirthday(signUpDto.getBirthday());
		user.updateRole(Role.USER);

		return SignUpUserDto.from(user);
	}
}
