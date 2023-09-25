package com.emotionbank.business.domain.signup.service;

import com.emotionbank.business.domain.signup.dto.SignUpDto;
import com.emotionbank.business.domain.signup.dto.SignUpUserDto;

public interface SignUpService {

	SignUpUserDto signup(SignUpDto signUpDto);
}
