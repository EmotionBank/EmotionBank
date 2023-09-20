package com.emotionbank.business.api.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.emotionbank.business.api.user.dto.UserSearchDto;
import com.emotionbank.business.api.user.dto.UserSimpleDto;
import com.emotionbank.business.common.BaseControllerTest;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.service.UserService;

@SpringBootTest
class UserControllerTest extends BaseControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	Pageable pageable;

	@BeforeEach
	public void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
			.build();
		when(userService.searchUser("닉네임", pageable)).thenReturn(new ArrayList<>());
		MockitoAnnotations.openMocks(this);
		pageable = PageRequest.of(0, 5);
	}

	@Test
	@DisplayName("응답 코드 확인")
	public void checkStatus() {
		String nickname = "닉네임";
		ResponseEntity<?> responseEntity = userController.searchUser(nickname, pageable);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	@DisplayName("응답 본문 확인")
	public void checkBody() {
		String nickname = "닉네임";
		ResponseEntity<?> responseEntity = userController.searchUser(nickname, pageable);
		UserSearchDto.Response responseBody = (UserSearchDto.Response)responseEntity.getBody();
		assertNotNull(responseBody);
		List<UserSimpleDto> users = responseBody.getUsers();
		assertTrue(users.isEmpty());
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
		MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
		requestParam.set("page", "1");
		requestParam.set("size", "5");

		//when
		mockMvc.perform(MockMvcRequestBuilders.get("/users/{nickname}", nickname)
			.params(requestParam)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		// verify(userService, times(1)).searchUser(nickname);
	}
}