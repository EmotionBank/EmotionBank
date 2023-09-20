package com.emotionbank.business.domain.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.emotionbank.business.domain.user.dto.FollowDto;
import com.emotionbank.business.domain.user.dto.UserDto;

public interface UserService {
	List<UserDto> searchUser(String userNickname, Pageable pageable);

	void followUser(FollowDto followDto);

	List<UserDto> getFollowees(Long userId);

	List<UserDto> getFollowers(Long userId);
}
