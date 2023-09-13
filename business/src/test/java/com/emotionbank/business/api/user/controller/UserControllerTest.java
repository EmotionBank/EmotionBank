package com.emotionbank.business.api.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emotionbank.business.common.BaseControllerTest;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


class UserControllerTest extends BaseControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;



	@BeforeEach
	public void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		when(userService.searchUser("닉네임")).thenReturn(new ArrayList<>());
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("응답 코드 확인")
	public void checkStatus() {
		String nickname = "닉네임";
		ResponseEntity<?> responseEntity = userController.searchUser(nickname);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	@Test
	@DisplayName("응답 본문 확인")
	public void checkBody() {
		String nickname = "닉네임";

		ResponseEntity<?> responseEntity = userController.searchUser(nickname);

		List<UserDto.UserSearchResultDto> responseBody = (List<UserDto.UserSearchResultDto>) responseEntity.getBody();
		assertNotNull(responseBody);
		assertTrue(responseBody.isEmpty());
	}
	// @Test
	// @DisplayName("UserService 호출 확인")
	// public void checkCallUserService() {
	// 	String nickname = "닉네임";
	// 	userController.searchUser(nickname);
	// 	verify(userService, times(1)).searchUser(nickname);
	// }


	@Test
	@DisplayName("유저를 검색한다")
	public void searchUser() throws Exception {
		//given
		String nickname = "닉네임";


		//when
		mockMvc.perform(
			MockMvcRequestBuilders.get("/users/{nickname}",nickname)
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());

		// verify(userService, times(1)).searchUser(nickname);
	}
}