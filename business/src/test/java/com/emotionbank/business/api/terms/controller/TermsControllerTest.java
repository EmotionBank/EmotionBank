package com.emotionbank.business.api.terms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.emotionbank.business.api.terms.dto.TermsCreateDto;
import com.emotionbank.business.common.BaseControllerTest;
import com.emotionbank.business.domain.auth.service.JwtManager;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;

class TermsControllerTest extends BaseControllerTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtManager jwtManager;

	@Test
	@DisplayName("약관 생성을 성공합니다")
	void createTerms() throws Exception {
		// given
		User saveuser = User.builder()
			.nickname("admin")
			.birthday(LocalDate.now())
			.role(Role.ADMIN)
			.socialId("12345678")
			.socialType(SocialType.KAKAO)
			.createdTime(LocalDateTime.now())
			.build();

		User user = userRepository.save(saveuser);

		TermsCreateDto.Request request =
			TermsCreateDto.Request.builder()
				.title("title")
				.content("content")
				.mandatory("ESSENTIAL")
				.build();

		String accessToken = jwtManager.createAccessToken(user.getUserId(), jwtManager.createAccessTokenExpireTime());

		// when
		mockMvc.perform(post("/terms")
				.header(HttpHeaders.AUTHORIZATION, getBearerToken(accessToken))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("termsId").exists())
			.andExpect(jsonPath("title").value("title"))
			.andExpect(jsonPath("content").value("content"))
			.andExpect(jsonPath("mandatory").value("ESSENTIAL"));
	}

	@Test
	@DisplayName("약관 생성을 실패합니다")
	void createTermsUnAuthorization() throws Exception {
		// given
		User saveuser = User.builder()
			.nickname("admin")
			.birthday(LocalDate.now())
			.role(Role.USER)
			.socialId("12345678")
			.socialType(SocialType.KAKAO)
			.createdTime(LocalDateTime.now())
			.build();

		User user = userRepository.save(saveuser);

		TermsCreateDto.Request request =
			TermsCreateDto.Request.builder()
				.title("title")
				.content("content")
				.mandatory("ESSENTIAL")
				.build();

		String accessToken = jwtManager.createAccessToken(user.getUserId(), jwtManager.createAccessTokenExpireTime());

		// when
		mockMvc.perform(post("/terms")
				.header(HttpHeaders.AUTHORIZATION, getBearerToken(accessToken))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andDo(print())
			.andExpect(status().isUnauthorized());
	}

	private String getBearerToken(String token) {
		return "Bearer " + token;
	}

}