package com.emotionbank.business.domain.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.emotionbank.business.domain.user.dto.FollowDto;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.entity.Follow;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.FollowRepository;
import com.emotionbank.business.domain.user.repository.UserRepository;

class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private FollowRepository followRepository;

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

		verify(userRepository, times(1)).findByNicknameContains("user", pageable);
	}

	@Test
	@DisplayName("유저를 팔로우 한다")
	void followUser() {
		User follower = User.builder()
			.userId(1L)
			.build();

		User followee = User.builder()
			.userId(2L)
			.build();

		when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
		when(userRepository.findById(2L)).thenReturn(Optional.of(followee));

		when(followRepository.findByFolloweeAndFollower(followee, follower)).thenReturn(Optional.empty());

		when(followRepository.save(any(Follow.class))).thenAnswer(invocation -> invocation.getArgument(0));

		FollowDto followDto = FollowDto.builder()
			.follower(1L)
			.followee(2L)
			.build();

		userService.followUser(followDto);

		verify(followRepository, times(1)).save(any(Follow.class));

	}

	@Test
	@DisplayName("유저를 언팔로우 한다")
	void unFollowUser() {
		User follower = User.builder()
			.userId(1L)
			.build();

		User followee = User.builder()
			.userId(2L)
			.build();

		Follow follow = Follow.builder()
			.followee(followee)
			.follower(follower)
			.build();

		when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
		when(userRepository.findById(2L)).thenReturn(Optional.of(followee));

		when(followRepository.findByFolloweeAndFollower(followee, follower)).thenReturn(Optional.of(follow));

		when(followRepository.save(any(Follow.class))).thenAnswer(invocation -> invocation.getArgument(0));

		FollowDto followDto = FollowDto.builder()
			.follower(1L)
			.followee(2L)
			.build();

		userService.followUser(followDto);
		verify(followRepository, times(1)).delete(any(Follow.class));
	}

	// @Test
	// @DisplayName("팔로잉 목록을 조회한다")
	// void showFollowingList() {
	//
	// 	User follower = User.builder()
	// 		.userId(1L)
	// 		.build();
	//
	// 	User followee = User.builder()
	// 		.userId(2L)
	// 		.build();
	//
	// 	Follow.builder()
	// 		.follower(follower)
	// 		.followee(followee)
	// 		.build();
	//
	// 	when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
	// 	when(followRepository.findByFollower(follower)).thenReturn()
	// }
}
