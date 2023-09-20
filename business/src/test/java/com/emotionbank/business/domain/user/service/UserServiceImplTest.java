package com.emotionbank.business.domain.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;

class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("유저를 검색한다")
	void searchUser() {
		List<User> fakeUsers = Arrays.asList(User.builder().build(), User.builder().build(), User.builder().build());
		Pageable pageable = PageRequest.of(0, 1);
		when(userRepository.findByNicknameContains("user", pageable)).thenReturn((fakeUsers));

		List<UserDto> result = userService.searchUser("user", pageable);

		assertThat(result).hasSize(3);

		verify(userRepository, times(1)).findByNicknameContains("%user%", pageable);
	}
}