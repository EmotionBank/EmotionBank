package com.emotionbank.business.domain.user.service;

import static com.emotionbank.business.global.error.ErrorCode.*;

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
		List<User> users = userRepository.findByNicknameContains(userNickname, pageable)
			.orElseThrow(NullPointerException::new);
		List<UserDto> userDtos = users.stream()
			.map(UserDto::from)
			.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void followUser(FollowDto followDto) {
		User follower = userRepository.findByNickname(followDto.getFollower())
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
		User followee = userRepository.findByNickname(followDto.getFollowee())
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

		followRepository.findByFolloweeAndFollower(followee, follower)
			.ifPresentOrElse(
				followRepository::delete,
				() -> followRepository.save(new Follow(follower, followee))
			);
	}

	@Override
	public void getFollowees(String userId) {

	}

	@Override
	public void getFollowers(String userId) {

	}
}
