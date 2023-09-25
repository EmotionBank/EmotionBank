package com.emotionbank.business.domain.signup.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emotionbank.business.domain.signup.dto.SignUpDto;
import com.emotionbank.business.domain.signup.dto.SignUpUserDto;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;

@SpringBootTest
class SignUpServiceTest {
	@Autowired
	UserRepository userRepository;

	@Autowired
	SignUpService signUpService;

	@Test
	@DisplayName("유저 회원가입을 완료하는 테스트")
	void signUp() {
		// given
		User saveuser = User.builder()
			.nickname("")
			.birthday(LocalDate.now())
			.role(Role.PENDING)
			.socialId("12345678")
			.socialType(SocialType.KAKAO)
			.createdTime(LocalDateTime.now())
			.build();

		User user = userRepository.save(saveuser);

		SignUpDto signUpDto = SignUpDto.builder()
			.userId(user.getUserId())
			.nickname("nickname")
			.birthday(LocalDate.of(1999, 01, 18))
			.build();

		// when
		SignUpUserDto userDto = signUpService.signup(signUpDto);

		assertThat(userDto.getUserId()).isEqualTo(user.getUserId());
		assertThat(userDto.getNickname()).isEqualTo(signUpDto.getNickname());
		assertThat(userDto.getBirthDay()).isEqualTo(signUpDto.getBirthday());
		assertThat(userDto.getRole()).isEqualTo(Role.USER.name());
	}
};