package com.emotionbank.business.domain.user.service;

import static com.emotionbank.business.global.error.ErrorCode.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.user.dto.FollowDto;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.entity.Follow;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.FollowRepository;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final FollowRepository followRepository;

	@Override
	public List<UserDto> searchUser(String userNickname, Pageable pageable) {
		List<User> users = userRepository.findByNicknameContains(userNickname, pageable);
		List<UserDto> userDtos = users.stream()
			.map(UserDto::from)
			.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void followUser(FollowDto followDto) {
		User follower = userRepository.findById(followDto.getFollower())
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
		User followee = userRepository.findById(followDto.getFollowee())
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

		followRepository.findByFolloweeAndFollower(followee, follower)
			.ifPresentOrElse(
				followRepository::delete,
				() -> followRepository.save(Follow.builder()
					.followee(followee)
					.follower(follower)
					.build())
			);
	}

	@Override
	public List<UserDto> getFollowees(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
		List<Follow> follows = followRepository.findByFollower(user).orElse(new ArrayList<>());
		List<User> users = follows.stream().map(Follow::getFollowee).collect(Collectors.toList());
		List<UserDto> userDtos = users.stream().map(UserDto::from).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public List<UserDto> getFollowers(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
		List<Follow> follows = followRepository.findByFollowee(user).orElse(new ArrayList<>());
		List<User> users = follows.stream().map(Follow::getFollower).collect(Collectors.toList());
		List<UserDto> userDtos = users.stream().map(UserDto::from).collect(Collectors.toList());
		return userDtos;
	}
}
