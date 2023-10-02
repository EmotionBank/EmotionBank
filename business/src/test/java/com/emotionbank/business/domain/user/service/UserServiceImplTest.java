package com.emotionbank.business.domain.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
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
	@Mock
	private AccountRepository accountRepository;

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
	@DisplayName("페이징을 적용하여 유저를 검색한다.")
	void searchUserByPaging() {
		/*
		todo: 직접 유저를 디비에 넣고 꺼낼 때 페이징이 적용되는지 확인한다.
		 */
	}

	@Test
	@DisplayName("유저를 팔로우 한다")
	void followUser() {
		Long followerId = 1L;
		Long followeeId = 2L;
		FollowDto followDto = FollowDto.of(followeeId, followerId);

		User follower = User.builder()
			.userId(followerId).build();

		User followee = User.builder()
			.userId(followeeId).build();

		when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
		when(userRepository.findById(followeeId)).thenReturn(Optional.of(followee));
		when(followRepository.findByFolloweeAndFollower(followee, follower)).thenReturn(Optional.empty());

		userService.followUser(followDto);

		verify(followRepository, times(1)).save(any(Follow.class));
		verify(followRepository, times(0)).delete(any(Follow.class));
	}

	@Test
	@DisplayName("유저를 언팔로우 한다")
	void unFollowUser() {
		Long followerId = 1L;
		Long followeeId = 2L;
		FollowDto followDto = FollowDto.of(followeeId, followerId);

		User follower = User.builder()
			.userId(followerId).build();

		User followee = User.builder()
			.userId(followeeId).build();

		when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
		when(userRepository.findById(followeeId)).thenReturn(Optional.of(followee));
		when(followRepository.findByFolloweeAndFollower(followee, follower)).thenReturn(
			Optional.of(Follow.of(follower, followee)));

		userService.followUser(followDto);

		verify(followRepository, times(0)).save(any(Follow.class));
		verify(followRepository, times(1)).delete(any(Follow.class));
	}

	@Test
	@DisplayName("팔로우 목록을 가지고 온다")
	public void getFollowees() {
		Long userId = 1L;
		User user = User.builder().userId(userId).build();

		List<Follow> follows = new ArrayList<>();
		Follow follow1 = Follow.of(user, user);
		Follow follow2 = Follow.of(user, user);
		follows.add(follow1);
		follows.add(follow2);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(followRepository.findByFollower(user)).thenReturn(follows);

		List<UserDto> followees = userService.getFollowees(userId);

		assertEquals(2, followees.size());
	}

	@Test
	@DisplayName("팔로워 목록을 가지고 온다")
	public void getFollowers() {
		Long userId = 1L;
		User user = User.builder().userId(userId).build();

		List<Follow> follows = new ArrayList<>();
		Follow follow1 = Follow.of(user, user);
		Follow follow2 = Follow.of(user, user);
		follows.add(follow1);
		follows.add(follow2);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(followRepository.findByFollowee(user)).thenReturn(follows);

		List<UserDto> followers = userService.getFollowers(userId);

		assertEquals(2, followers.size());
	}

	@Test
	@DisplayName("회원정보를 조회한다")
	public void getMyInfo() {
		Long userId = 1L;
		User user = User.builder()
			.userId(userId)
			.build();

		when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));
		UserDto userInfo = userService.getUserInfo(userId);

		assertThat(userInfo.getUserId()).isEqualTo(userId);
	}

	@Test
	@DisplayName("내 상세정보를 조회한다")
	public void getMyProfile() {
		User user = User.builder()
			.userId(1L)
			.nickname("nickname")
			.build();
		Account account = Account.builder()
			.accountId(1L)
			.user(user)
			.build();

		List<Follow> follows = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			follows.add(Follow.builder().build());
		}

		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
		when(accountRepository.findByUser(user)).thenReturn(Optional.ofNullable(account));
		when(followRepository.findByFollower(user)).thenReturn(follows);
		when(followRepository.findByFollowee(user)).thenReturn(follows);

		UserDto myProfile = userService.getMyProfile(1L);
		assertEquals(myProfile.getFollower(), 3);
		assertEquals(myProfile.getFollowing(), 3);
		assertEquals(myProfile.getNickname(), "nickname");
	}

	@Test
	@DisplayName("타인 상세정보를 조회한다")
	public void getOtherProfile() {
		User user = User.builder()
			.userId(1L)
			.nickname("nickname")
			.build();

		List<Follow> follows = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			follows.add(Follow.builder().build());
		}

		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
		when(followRepository.findByFollower(user)).thenReturn(follows);
		when(followRepository.findByFollowee(user)).thenReturn(follows);

		UserDto myProfile = userService.getOtherProfile(1L);
		assertEquals(myProfile.getFollower(), 3);
		assertEquals(myProfile.getFollowing(), 3);
		assertEquals(myProfile.getNickname(), "nickname");
	}
}
