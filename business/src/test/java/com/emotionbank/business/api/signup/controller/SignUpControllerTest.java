package com.emotionbank.business.api.signup.controller;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.emotionbank.business.api.signup.dto.RequestSignUpDto;
import com.emotionbank.business.common.BaseControllerTest;
import com.emotionbank.business.domain.auth.service.JwtManager;
import com.emotionbank.business.domain.signup.service.SignUpService;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;

@SpringBootTest
class SignUpControllerTest extends BaseControllerTest {
	@Autowired
	SignUpService signUpService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtManager jwtManager;

	@Test
	@DisplayName("회원가입에 성공하는 테스트")
	void signUp() throws Exception {
		User saveuser = User.builder()
			.nickname("")
			.birthday(LocalDate.now())
			.role(Role.PENDING)
			.socialId("12345678")
			.socialType(SocialType.KAKAO)
			.createdTime(LocalDateTime.now())
			.build();

		User user = userRepository.save(saveuser);

		String accessToken = jwtManager.createAccessToken(user.getUserId(), jwtManager.createAccessTokenExpireTime());

		RequestSignUpDto.Request request = RequestSignUpDto.Request.builder()
			.nickname("Register")
			.birthday("1999-01-18")
			.build();

		mockMvc.perform(post("/signup")
				.header(HttpHeaders.AUTHORIZATION, getBearerToken(accessToken))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("userId").value(user.getUserId()))
			.andExpect(jsonPath("nickname").value("Register"))
			.andExpect(jsonPath("birthday").value("1999-01-18"))
			.andExpect(jsonPath("role").value("USER"));
	}

	@Test
	@DisplayName("이미 회원가입 한 회원을 가입하는 테스트")
	void signUp_409() throws Exception {
		User saveuser = User.builder()
			.nickname("")
			.birthday(LocalDate.now())
			.role(Role.USER)
			.socialId("12345678")
			.socialType(SocialType.KAKAO)
			.createdTime(LocalDateTime.now())
			.build();

		User user = userRepository.save(saveuser);

		String accessToken = jwtManager.createAccessToken(user.getUserId(), jwtManager.createAccessTokenExpireTime());

		RequestSignUpDto.Request request = RequestSignUpDto.Request.builder()
			.nickname("Register")
			.birthday("1999-01-18")
			.build();

		mockMvc.perform(post("/signup")
				.header(HttpHeaders.AUTHORIZATION, getBearerToken(accessToken))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andDo(print())
			.andExpect(status().isConflict());
	}

	private String getBearerToken(String token) {
		return "Bearer " + token;
	}
}