package com.emotionbank.business.domain.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public List<UserDto.UserSearchResultDto> searchUser(String userNickname, Pageable pageable) {
		List<User> users = userRepository.findByNicknameContains(userNickname, pageable)
			.orElseThrow(NullPointerException::new);
		List<UserDto.UserSearchResultDto> userSearchResultDtos = users.stream()
			.map(UserDto.UserSearchResultDto::from)
			.collect(Collectors.toList());
		return userSearchResultDtos;
	}

	@Override
	public void followUser(String userId) {

	}

	@Override
	public void getFollowees(String userId) {

	}

	@Override
	public void getFollowers(String userId) {

	}
}
