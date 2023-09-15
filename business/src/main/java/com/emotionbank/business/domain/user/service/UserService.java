package com.emotionbank.business.domain.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.emotionbank.business.domain.user.dto.UserDto;

public interface UserService {
	List<UserDto.UserSearchResultDto> searchUser(String userId, Pageable pageable);

	void followUser(String userId);

	void getFollowees(String userId);

	void getFollowers(String userId);
}
